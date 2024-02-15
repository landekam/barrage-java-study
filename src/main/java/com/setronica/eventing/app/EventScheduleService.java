package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.EventRepository;
import com.setronica.eventing.persistence.EventSchedule;
import com.setronica.eventing.persistence.EventScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventScheduleService {
    private final EventScheduleRepository eventScheduleRepository;
    private final EventRepository eventRepository;

    public EventScheduleService(EventScheduleRepository eventScheduleRepository, EventRepository eventRepository) {
        this.eventScheduleRepository = eventScheduleRepository;
        this.eventRepository = eventRepository;
    }

    public List<EventSchedule> getAll() {
        return eventScheduleRepository.findAll();
    }

    public EventSchedule getById(Integer id) {
        return eventScheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event schedule not found with id=" + id));
    }

    public EventSchedule createSchedule(EventSchedule eventSchedule) {
        eventRepository.findById(eventSchedule.getEventId()).orElseThrow(() -> new NotFoundException("Event not found with id=" + eventSchedule.getEventId()));
        EventSchedule newEventSchedule = eventScheduleRepository.save(eventSchedule);
        log.info("Created event schedule with id: " + newEventSchedule.getId());
        return newEventSchedule;
    }

    public EventSchedule updateSchedule(EventSchedule eventSchedule) {
        eventRepository.findById(eventSchedule.getEventId()).orElseThrow(() -> new NotFoundException("Event not found with id=" + eventSchedule.getEventId()));
        getById(eventSchedule.getId());
        EventSchedule updatedEventSchedule = eventScheduleRepository.save(eventSchedule);
        log.info("Updated event schedule with id: " + updatedEventSchedule.getId());
        return updatedEventSchedule;
    }

    public void deleteSchedule(int id) {
        getById(id);
        eventScheduleRepository.deleteById(id);
        log.info("Deleted event schedule with id: " + id);
    }
}
