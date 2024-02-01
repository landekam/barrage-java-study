package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.ApplicationLogicException;
import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.EventSchedule;
import com.setronica.eventing.persistence.EventScheduleRepository;
import org.springframework.dao.DataAccessException;
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
        try {
            return eventScheduleRepository.save(eventSchedule);
        } catch (DataAccessException e) {
            throw new ApplicationLogicException("something_went_wrong", e);
        }
    }

    public EventSchedule updateSchedule(EventSchedule eventSchedule) {
        try {
            return eventScheduleRepository.save(eventSchedule);
        } catch (DataAccessException e) {
            throw new ApplicationLogicException("something_went_wrong", e);
        }
    }

    public void deleteSchedule(int id) {
        eventScheduleRepository.deleteById(id);
    }
}
