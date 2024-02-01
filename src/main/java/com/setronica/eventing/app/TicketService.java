package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.ApplicationLogicException;
import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.TicketOrder;
import com.setronica.eventing.persistence.TicketRepository;
import org.springframework.dao.DataAccessException;
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
        try {
            return ticketRepository.save(ticket);
        } catch (DataAccessException e) {
            throw new ApplicationLogicException("something_went_wrong", e);
        }
    }

    public TicketOrder updateTicket(TicketOrder ticket) {
        try {
            return ticketRepository.save(ticket);
        } catch (DataAccessException e) {
            throw new ApplicationLogicException("something_went_wrong", e);
        }
    }

    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }
}
