package com.setronica.eventing.web;

import com.setronica.eventing.app.PaymentService;
import com.setronica.eventing.dto.PaymentDto;
import com.setronica.eventing.mapper.PaymentMapper;
import com.setronica.eventing.persistence.Payment;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event/api/v1/payments")
public class PaymentController {
  private final PaymentService paymentService;
  private final PaymentMapper paymentMapper;

  public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
    this.paymentService = paymentService;
    this.paymentMapper = paymentMapper;
  }

  @GetMapping
  @Operation(tags = {"Payment management"}, summary = "Returns a list of payments")
  public List<Payment> findAll() {
    return paymentService.getAll();
  }

  @GetMapping("/{id}")
  @Operation(tags = {"Payment management"}, summary = "Returns a payment by id")
  public PaymentDto getById(@PathVariable Integer id) {
    Payment entity = paymentService.getById(id);
    return paymentMapper.mapToDto(entity);
  }

  @PostMapping
  @Operation(tags = {"Payment management"}, summary = "Creates a payment")
  public PaymentDto createPayment(@RequestBody PaymentDto dto) {
    Payment payment = paymentMapper.mapToPayment(dto);
    Payment createdPayment = paymentService.createPayment(payment);
    return paymentMapper.mapToDto(createdPayment);
  }

  @PostMapping("{id}/refund")
  @Operation(tags = {"Payment management"}, summary = "Refunds a payment")
  public PaymentDto refundPayment(@PathVariable Integer id) {
    Payment refundedPayment = paymentService.refundPayment(id);
    return paymentMapper.mapToDto(refundedPayment);
  }

}
