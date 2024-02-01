package com.setronica.eventing.mapper;

import com.setronica.eventing.dto.TicketDto;
import com.setronica.eventing.exceptions.BadRequestException;
import com.setronica.eventing.persistence.TicketOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TicketMapper {
    public abstract TicketDto mapToDto(TicketOrder entity);

    public abstract TicketOrder mapToTicket(TicketDto dto);

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
