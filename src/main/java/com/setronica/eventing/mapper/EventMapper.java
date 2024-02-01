package com.setronica.eventing.mapper;

import com.setronica.eventing.dto.EventDto;
import com.setronica.eventing.exceptions.BadRequestException;
import com.setronica.eventing.persistence.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import java.time.LocalDate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EventMapper {

    public abstract EventDto mapToDto(Event entity);

    public abstract Event mapToEvent(EventDto dto);

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
