package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.EventSchedule;
import com.setronica.eventing.persistence.EventScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return eventScheduleRepository.save(eventSchedule);

    }

    public EventSchedule updateSchedule(EventSchedule eventSchedule) {
        getById(eventSchedule.getId());
        return eventScheduleRepository.save(eventSchedule);
    }

    public void deleteSchedule(int id) {
        getById(id);
        eventScheduleRepository.deleteById(id);
    }
}
