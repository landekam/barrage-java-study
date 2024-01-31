package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.Event;
import com.setronica.eventing.persistence.EventRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event get(int id) {
        Optional<Event> result = eventRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("event_not_found");
        }
        return result.get();
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public List<Event> search(String searchQuery) {
        List<Event> eventList = eventRepository.findAll();

        return filterEventsBySearchQuery(eventList, searchQuery);
    }

    private List<Event> filterEventsBySearchQuery(List<Event> events, String searchQuery) {
        return events.stream()
                // search both title and description, make the search case insensitive
                .filter(event -> event.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        event.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                .toList();
    }

    public Event create(Event newEvent) {
        return eventRepository.save(newEvent);
    }

    public Event update(Integer id, Event newEvent) {
        get(id);

        newEvent.setId(id);

        return eventRepository.save(newEvent);
    }

    public void delete(Integer id) {
        get(id);

        eventRepository.deleteById(id);
    }

}
