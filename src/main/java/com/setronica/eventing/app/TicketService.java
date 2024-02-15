package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.EventScheduleRepository;
import com.setronica.eventing.persistence.TicketOrder;
import com.setronica.eventing.persistence.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    private final EventScheduleRepository eventScheduleRepository;

    public TicketService(TicketRepository ticketRepository, EventScheduleRepository eventScheduleRepository) {
        this.ticketRepository = ticketRepository;
        this.eventScheduleRepository = eventScheduleRepository;
    }

    public List<TicketOrder> getAll() {
        return ticketRepository.findAll();
    }

    public TicketOrder getById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found with id=" + id));
    }

    public TicketOrder createTicket(TicketOrder ticket) {
        eventScheduleRepository.findById(ticket.getEventScheduleId()).orElseThrow(() -> new NotFoundException("Event schedule not found with id=" + ticket.getEventScheduleId()));
        TicketOrder newTicketOrder = ticketRepository.save(ticket);
        log.info("Created ticket order with id: " + newTicketOrder.getId());
        return newTicketOrder;
    }

    public TicketOrder updateTicket(TicketOrder ticket) {
        eventScheduleRepository.findById(ticket.getEventScheduleId()).orElseThrow(() -> new NotFoundException("Event schedule not found with id=" + ticket.getEventScheduleId()));
        getById(ticket.getId());
        TicketOrder updatedTicketOrder =  ticketRepository.save(ticket);
        log.info("Updated ticket order with id: " + updatedTicketOrder.getId());
        return updatedTicketOrder;
    }

    public void deleteTicket(int id) {
        getById(id);
        ticketRepository.deleteById(id);
        log.info("Deleted ticket order with id: " + id);
    }
}
