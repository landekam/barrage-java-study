package com.setronica.eventing.app;

import com.setronica.eventing.dto.PaymentResultDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

  @RabbitListener(queues = {"payment-notifications"})
  public void readPaymentEvent(PaymentResultDto message) {
    System.out.println("Processing payment: " + message.getPayment());
    if (message.getState().equals("AUTHORIZED")) {
      System.out.println("Payment authorized...");
      // do something here
    }
    else if (message.getState().equals("FAILED")) {
      System.out.println("Payment failed...");
      // do something here
    }
  }
}
