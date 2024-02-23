package com.setronica.eventing.web;

import com.setronica.eventing.app.TicketService;
import com.setronica.eventing.dto.TicketDto;
import com.setronica.eventing.mapper.TicketMapper;
import com.setronica.eventing.persistence.TicketOrder;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(tags = {"Ticket order management"}, summary = "Returns a list of ticket orders")
    public List<TicketOrder> findAll() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(tags = {"Ticket order management"}, summary = "Returns a ticket order by id")
    public TicketDto getById(@PathVariable Integer id) {
        TicketOrder entity = ticketService.getById(id);
        return ticketMapper.mapToDto(entity);
    }

    @PostMapping
    @Operation(tags = {"Ticket order management"}, summary = "Creates a ticket order")
    public TicketDto createTicket(@RequestBody TicketDto dto) {
        TicketOrder ticket = ticketMapper.mapToTicket(dto);
        TicketOrder createdTicket = ticketService.createTicket(ticket);
        return ticketMapper.mapToDto(createdTicket);
    }

    @PutMapping("/{id}")
    @Operation(tags = {"Ticket order management"}, summary = "Updates a ticket order")
    public TicketDto updateTicket(@RequestBody TicketDto dto, @PathVariable Integer id) {
        TicketOrder ticket = ticketMapper.mapToTicket(dto);
        TicketOrder createdTicket = ticketService.updateTicket(ticket, id);
        return ticketMapper.mapToDto(createdTicket);
    }

    @DeleteMapping("/{id}")
    @Operation(tags = {"Ticket order management"}, summary = "Deletes a ticket order")
    public ResponseEntity<Void> deleteTicket(@PathVariable int id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }
}
