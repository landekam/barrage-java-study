package com.setronica.eventing.app;

import com.setronica.eventing.exceptions.BadRequestException;
import com.setronica.eventing.exceptions.NotFoundException;
import com.setronica.eventing.persistence.EventSchedule;
import com.setronica.eventing.persistence.EventScheduleRepository;
import com.setronica.eventing.persistence.Payment;
import com.setronica.eventing.persistence.PaymentRepository;
import com.setronica.eventing.persistence.TicketOrder;
import com.setronica.eventing.persistence.TicketRepository;
import com.setronica.eventing.persistence.TicketStatus;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {
  private final PaymentRepository paymentRepository;
  private final TicketRepository ticketRepository;
  private final EventScheduleRepository eventScheduleRepository;

  public PaymentService(PaymentRepository paymentRepository, TicketRepository ticketRepository, EventScheduleRepository eventScheduleRepository){
    this.paymentRepository = paymentRepository;
    this.ticketRepository = ticketRepository;
    this.eventScheduleRepository = eventScheduleRepository;
  }

  public List<Payment> getAll() {
    return paymentRepository.findAll();
  }

  public Payment getById(Integer id) {
    return paymentRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found with id=" + id));
  }

  public Payment createPayment(Payment payment) {
    TicketOrder ticketOrder = ticketRepository.findById(payment.getTicketOrderId()).orElseThrow(() -> new NotFoundException("Ticket order not found with id=" + payment.getTicketOrderId()));
    EventSchedule eventSchedule = eventScheduleRepository.findById(ticketOrder.getEventScheduleId()).orElseThrow(() -> new NotFoundException("Event schedule not found with id=" + ticketOrder.getEventScheduleId()));

    if (ticketOrder.getStatus().equals(TicketStatus.SALE) || ticketOrder.getStatus().equals(TicketStatus.REFUNDED)) {
      throw new BadRequestException("Payment for ticket order with id " + ticketOrder.getId() + " already exists");
    }

    if (payment.getAmount().compareTo(eventSchedule.getPrice().multiply(BigDecimal.valueOf(ticketOrder.getAmount()))) == 0) {
      payment.setSuccessful(true);
    }
    else {
      payment.setSuccessful(false);
    }

    Payment newPayment = paymentRepository.save(payment);
    log.info("Created payment with id: " + newPayment.getId());

    if (payment.getSuccessful()) {
      ticketOrder.setStatus(TicketStatus.SALE);
      ticketRepository.save(ticketOrder);
      log.info("Updated ticket order with id: " + ticketOrder.getId());
    }

    return newPayment;
  }

  public Payment refundPayment(Integer id) {
    Payment originalPayment = getById(id);
    TicketOrder ticketOrder = ticketRepository.findById(originalPayment.getTicketOrderId()).orElseThrow(() -> new NotFoundException("Ticket order not found with id=" + originalPayment.getTicketOrderId()));
    if (ticketOrder.getStatus().equals(TicketStatus.REFUNDED)) {
      throw new BadRequestException("Ticket order with id " + ticketOrder.getId() + " already refunded");
    }

    Payment refundPayment = new Payment();
    refundPayment.setAmount(originalPayment.getAmount().multiply(BigDecimal.valueOf(-1.0)));
    refundPayment.setTicketOrderId(originalPayment.getTicketOrderId());
    refundPayment.setSuccessful(originalPayment.getSuccessful());

    Payment newPayment = paymentRepository.save(refundPayment);
    log.info("Successfully refunded payment with id: " + originalPayment.getId() + " and created payment with id: " + newPayment.getId());

    if (newPayment.getSuccessful()) {
      ticketOrder.setStatus(TicketStatus.REFUNDED);
      ticketRepository.save(ticketOrder);
      log.info("Updated ticket order with id: " + ticketOrder.getId());
    }

    return newPayment;
  }

}
