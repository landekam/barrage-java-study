package com.setronica.eventing.web;

import com.setronica.eventing.app.EventService;
import com.setronica.eventing.dto.EventDto;
import com.setronica.eventing.mapper.EventMapper;
import com.setronica.eventing.persistence.Event;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event/api/v1/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping
    @Operation(tags = {"Event management"}, summary = "Returns a list of events")
    public List<Event> findAll() {
        return eventService.getAll();
    }

    @GetMapping("search")
    @Operation(tags = {"Event management"}, summary = "Returns a list of events filtered by search query")
    public List<Event> searchEvents(
            @RequestParam String searchQuery) {
        return eventService.search(searchQuery);
    }

    @GetMapping("/{id}")
    @Operation(tags = {"Event management"}, summary = "Returns an event by its id")
    public EventDto getById(@PathVariable Integer id) {
        Event entity = eventService.getById(id);
        return eventMapper.mapToDto(entity);
    }

    @PostMapping("")
    @Operation(tags = {"Event management"}, summary = "Creates a new event")
    public EventDto createEvent(@RequestBody EventDto dto) {
        Event event = eventMapper.mapToEvent(dto);
        Event createdEvent = eventService.createEvent(event);
        return eventMapper.mapToDto(createdEvent);
    }

    @PutMapping("/{id}")
    @Operation(tags = {"Event management"}, summary = "Updates an event")
    public EventDto updateEvent(@RequestBody EventDto dto, @PathVariable Integer id) {
        dto.setId(id);
        Event event = eventMapper.mapToEvent(dto);
        Event createdEvent = eventService.updateEvent(event);
        return eventMapper.mapToDto(createdEvent);
    }

    @DeleteMapping("/{id}")
    @Operation(tags = {"Event management"}, summary = "Deletes an event")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") int id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
