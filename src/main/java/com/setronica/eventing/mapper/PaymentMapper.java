package com.setronica.eventing.mapper;

import com.setronica.eventing.dto.PaymentDto;
import com.setronica.eventing.exceptions.BadRequestException;
import com.setronica.eventing.persistence.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PaymentMapper {
  public abstract PaymentDto mapToDto(Payment entity);

  public abstract Payment mapToPayment(PaymentDto dto);

  protected Integer map(Integer value) {
    validateNotNull(value);
    return value;
  }

  protected Double map(Double value) {
    validateNotNull(value);
    return value;
  }

  protected Boolean map(Boolean value) {
    validateNotNull(value);
    return value;
  }

  protected void validateNotNull(Object value) {
    if (value == null) {
      throw new BadRequestException("Value cannot be null");
    }
  }
}
