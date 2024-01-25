package com.setronica.eventing.app;

import com.setronica.eventing.persistence.Event;
import com.setronica.eventing.persistence.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
                        event.getDescription().toLowerCase().contains(searchQuery.toLowerCase())
                )
                .toList();
    }

}
