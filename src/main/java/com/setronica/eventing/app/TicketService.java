package com.setronica.eventing.app;

import com.setronica.eventing.dto.SaleDto;
import com.setronica.eventing.exceptions.BadRequestException;
import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.Event;
import com.setronica.eventing.persistence.EventRepository;
import com.setronica.eventing.persistence.EventSchedule;
import com.setronica.eventing.persistence.EventScheduleRepository;
import com.setronica.eventing.persistence.TicketOrder;
import com.setronica.eventing.persistence.TicketRepository;
import com.setronica.eventing.persistence.TicketStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    private final EventScheduleRepository eventScheduleRepository;

    private final EventRepository eventRepository;

    private final RabbitTemplate rabbitTemplate;

    public TicketService(TicketRepository ticketRepository, EventScheduleRepository eventScheduleRepository, EventRepository eventRepository, RabbitTemplate rabbitTemplate) {
        this.ticketRepository = ticketRepository;
        this.eventScheduleRepository = eventScheduleRepository;
        this.eventRepository = eventRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<TicketOrder> getAll() {
        return ticketRepository.findAll();
    }

    public TicketOrder getById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found with id=" + id));
    }

    public TicketOrder createTicket(TicketOrder ticket) {
        ticket.setStatus(TicketStatus.BOOKED);

        EventSchedule eventSchedule = eventScheduleRepository.getReferenceById(ticket.getEventScheduleId());
        Event event = eventRepository.getReferenceById(eventSchedule.getEventId());

        TicketOrder newTicketOrder = ticketRepository.save(ticket);

        log.info("Created ticket order with id: " + newTicketOrder.getId());

        SaleDto message = new SaleDto(newTicketOrder.getEmail(), event.getTitle(), newTicketOrder.getAmount());

        rabbitTemplate.convertAndSend("ticket-sale", message);

        return newTicketOrder;
    }

    public TicketOrder updateTicket(TicketOrder ticket, Integer id) {
        ticket.setId(id);
        eventScheduleRepository.findById(ticket.getEventScheduleId()).orElseThrow(
                () -> new NotFoundException("Event schedule not found with id=" + ticket.getEventScheduleId()));
        TicketOrder ticketToUpdate = getById(ticket.getId());
        if (ticketToUpdate.getStatus().equals(TicketStatus.REFUNDED)
                || ticketToUpdate.getStatus().equals(TicketStatus.SALE)) {
            throw new BadRequestException(
                    "Ticket order with id " + ticketToUpdate.getId() + " already payed for and cannot be changed");
        }
        ticket.setStatus(ticketToUpdate.getStatus());
        TicketOrder updatedTicketOrder = ticketRepository.save(ticket);
        log.info("Updated ticket order with id: " + updatedTicketOrder.getId());
        return updatedTicketOrder;
    }

    public void deleteTicket(int id) {
        getById(id);
        ticketRepository.deleteById(id);
        log.info("Deleted ticket order with id: " + id);
    }
}
