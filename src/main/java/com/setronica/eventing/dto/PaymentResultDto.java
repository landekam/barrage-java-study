package com.setronica.eventing.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResultDto {
  private int payment; //payment id
  private BigDecimal amount; //payment amount
  private String state; //payment state
}
