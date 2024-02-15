package com.setronica.eventing.web;

import com.setronica.eventing.app.TicketService;
import com.setronica.eventing.dto.TicketDto;
import com.setronica.eventing.mapper.TicketMapper;
import com.setronica.eventing.persistence.TicketOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event/api/v1/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping
    public List<TicketOrder> findAll() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public TicketDto getById(@PathVariable Integer id) {
        TicketOrder entity = ticketService.getById(id);
        return ticketMapper.mapToDto(entity);
    }

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto dto) {
        dto.setStatus("BOOKED");
        TicketOrder ticket = ticketMapper.mapToTicket(dto);
        TicketOrder createdTicket = ticketService.createTicket(ticket);
        return ticketMapper.mapToDto(createdTicket);
    }

    @PutMapping("/{id}")
    public TicketDto updateTicket(@RequestBody TicketDto dto, @PathVariable Integer id) {
        dto.setStatus("BOOKED");
        dto.setId(id);
        TicketOrder ticket = ticketMapper.mapToTicket(dto);
        TicketOrder createdTicket = ticketService.updateTicket(ticket);
        return ticketMapper.mapToDto(createdTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }
}
