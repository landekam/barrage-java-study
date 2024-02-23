package com.setronica.eventing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaleDto {
  private String email; //customer email address
  private String title; //event title
  private int amount; //total amount of the orders
}
