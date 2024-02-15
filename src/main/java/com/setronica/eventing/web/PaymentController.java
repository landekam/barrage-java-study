package com.setronica.eventing.web;

import com.setronica.eventing.app.PaymentService;
import com.setronica.eventing.dto.PaymentDto;
import com.setronica.eventing.mapper.PaymentMapper;
import com.setronica.eventing.persistence.Payment;
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
  public List<Payment> findAll() {
    return paymentService.getAll();
  }

  @GetMapping("/{id}")
  public PaymentDto getById(@PathVariable Integer id) {
    Payment entity = paymentService.getById(id);
    return paymentMapper.mapToDto(entity);
  }

  @PostMapping
  public PaymentDto createPayment(@RequestBody PaymentDto dto) {
    Payment payment = paymentMapper.mapToPayment(dto);
    Payment createdPayment = paymentService.createPayment(payment);
    return paymentMapper.mapToDto(createdPayment);
  }

  @PostMapping("{id}/refund")
  public PaymentDto refundPayment(@PathVariable Integer id) {
    Payment refundedPayment = paymentService.refundPayment(id);
    return paymentMapper.mapToDto(refundedPayment);
  }

}
