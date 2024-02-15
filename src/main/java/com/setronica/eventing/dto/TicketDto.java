package com.setronica.eventing.dto;

import com.setronica.eventing.persistence.TicketStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDto {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer amount;
    private Integer eventScheduleId;
    private TicketStatus status;
}
