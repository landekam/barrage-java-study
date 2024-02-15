package com.setronica.eventing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer amount;
    private Integer eventScheduleId;
    private String status;
}
