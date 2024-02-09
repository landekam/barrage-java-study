package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.Event;
import com.setronica.eventing.persistence.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found with id=" + id));
    }

    public List<Event> search(String searchQuery) {
        return eventRepository.searchByTitle(searchQuery);
    }

    public Event createEvent(Event event) {
        Event newEvent = eventRepository.save(event);
        log.info("Created new event with id: " + newEvent.getId());
        return newEvent;
    }

    public Event updateEvent(Event event) {
        getById(event.getId());
        Event updatedEvent = eventRepository.save(event);
        log.info("Updated event with id: " + updatedEvent.getId());
        return updatedEvent;
    }

    public void deleteEvent(int id) {
        getById(id);
        eventRepository.deleteById(id);
        log.info("Deleted event with id: " + id);
    }
}
