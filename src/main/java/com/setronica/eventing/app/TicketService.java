package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.TicketOrder;
import com.setronica.eventing.persistence.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketOrder> getAll() {
        return ticketRepository.findAll();
    }

    public TicketOrder getById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found with id=" + id));
    }

    public TicketOrder createTicket(TicketOrder ticket) {
        return ticketRepository.save(ticket);
    }

    public TicketOrder updateTicket(TicketOrder ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }
}
