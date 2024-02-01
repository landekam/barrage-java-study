package com.setronica.eventing.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter

public class EventScheduleDto {
    private int id;
    private Integer eventId;
    private LocalDate eventDate;
    private Integer availableSeats;
    private BigDecimal price;
}
