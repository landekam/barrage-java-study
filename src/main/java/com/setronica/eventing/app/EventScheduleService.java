package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.EventSchedule;
import com.setronica.eventing.persistence.EventScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventScheduleService {
    private final EventScheduleRepository eventScheduleRepository;

    public EventScheduleService(EventScheduleRepository eventScheduleRepository) {
        this.eventScheduleRepository = eventScheduleRepository;
    }

    public List<EventSchedule> getAll() {
        return eventScheduleRepository.findAll();
    }

    public EventSchedule getById(Integer id) {
        return eventScheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event schedule not found with id=" + id));
    }

    public EventSchedule createSchedule(EventSchedule eventSchedule) {
        EventSchedule newEventSchedule = eventScheduleRepository.save(eventSchedule);
        log.info("Created event schedule with id: " + newEventSchedule.getId());
        return newEventSchedule;
    }

    public EventSchedule updateSchedule(EventSchedule eventSchedule) {
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
