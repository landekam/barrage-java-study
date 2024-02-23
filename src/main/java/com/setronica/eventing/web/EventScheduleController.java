package com.setronica.eventing.web;

import com.setronica.eventing.app.EventScheduleService;
import com.setronica.eventing.dto.EventScheduleDto;
import com.setronica.eventing.mapper.EventScheduleMapper;
import com.setronica.eventing.persistence.EventSchedule;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event/api/v1/events-schedule")
public class EventScheduleController {

    private final EventScheduleService eventScheduleService;
    private final EventScheduleMapper eventScheduleMapper;

    public EventScheduleController(EventScheduleService eventScheduleService, EventScheduleMapper eventScheduleMapper) {
        this.eventScheduleService = eventScheduleService;
        this.eventScheduleMapper = eventScheduleMapper;
    }

    @GetMapping
    @Operation(tags = {"Event schedule management"}, summary = "Returns a list of event schedules")
    public List<EventSchedule> findAll() {
        return eventScheduleService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(tags = {"Event schedule management"}, summary = "Returns an event schedule by its id")
    public EventScheduleDto getById(@PathVariable Integer id) {
        EventSchedule entity = eventScheduleService.getById(id);
        return eventScheduleMapper.mapToDto(entity);
    }

    @PostMapping
    @Operation(tags = {"Event schedule management"}, summary = "Creates a new event schedule")
    public EventScheduleDto createSchedule(@RequestBody EventScheduleDto dto) {
        EventSchedule eventSchedule = eventScheduleMapper.mapToEventSchedule(dto);
        EventSchedule createdEventSchedule = eventScheduleService.createSchedule(eventSchedule);
        return eventScheduleMapper.mapToDto(createdEventSchedule);
    }

    @PutMapping("/{id}")
    @Operation(tags = {"Event schedule management"}, summary = "Updates an event schedule")
    public EventScheduleDto updateSchedule(@RequestBody EventScheduleDto dto, @PathVariable Integer id) {
        dto.setId(id);
        EventSchedule eventSchedule = eventScheduleMapper.mapToEventSchedule(dto);
        EventSchedule createdSchedule = eventScheduleService.updateSchedule(eventSchedule);
        return eventScheduleMapper.mapToDto(createdSchedule);
    }

    @DeleteMapping("/{id}")
    @Operation(tags = {"Event schedule management"}, summary = "Deletes an event schedule")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int id) {
        eventScheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
