package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.ApplicationLogicException;
import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.Event;
import com.setronica.eventing.persistence.EventRepository;
import org.springframework.dao.DataAccessException;
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

    public Event getById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found with id=" + id));
    }

    public Event createEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (DataAccessException e) {
            throw new ApplicationLogicException("something_went_wrong", e);
        }

    }

    public Event updateEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (DataAccessException e) {
            throw new ApplicationLogicException("something_went_wrong", e);
        }
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
}
