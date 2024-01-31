package com.setronica.eventing.web;

import com.setronica.eventing.app.EventService;
import com.setronica.eventing.persistence.Event;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("{id}")
    public Event get(@PathVariable("id") int id) {
            return eventService.get(id);
    }

    @GetMapping
    public List<Event> findAll() {

        return eventService.getAll();
    }

    @GetMapping("search")
    public List<Event> searchEvents(
            @RequestParam String searchQuery
    ) {
        return  eventService.search(searchQuery);
    }

    @PostMapping
    public Event create(
            @RequestBody Event newEvent
    ) {
        return eventService.create(newEvent);
    }

    @PutMapping("{id}")
    public Event update(@PathVariable("id") int id, @RequestBody Event updateEvent) {
            return eventService.update(id, updateEvent);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
            eventService.delete(id);
    }
}
