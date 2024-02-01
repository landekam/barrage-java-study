package com.setronica.eventing.mapper;

import com.setronica.eventing.dto.EventScheduleDto;
import com.setronica.eventing.exceptions.BadRequestException;
import com.setronica.eventing.persistence.EventSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.time.LocalDate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EventScheduleMapper {
    public abstract EventScheduleDto mapToDto(EventSchedule entity);

    public abstract EventSchedule mapToEventSchedule(EventScheduleDto dto);

    protected BigDecimal map(BigDecimal value) {
        validateNotNull(value);
        return value;
    }

    protected LocalDate map(LocalDate value) {
        validateNotNull(value);
        return value;
    }

    protected String map(String value) {
        validateNotNull(value);
        return value;
    }

    protected Integer map(Integer value) {
        validateNotNull(value);
        return value;
    }

    protected void validateNotNull(Object value) {
        if (value == null) {
            throw new BadRequestException("Value cannot be null");
        }
    }

}