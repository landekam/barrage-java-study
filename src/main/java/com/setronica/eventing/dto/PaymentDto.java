package com.setronica.eventing.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
  private int id;
  private BigDecimal amount;
  private Integer ticketOrderId;
  private Boolean successful;
}
