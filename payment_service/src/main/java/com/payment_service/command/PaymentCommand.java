package com.payment_service.command;


import com.payment_service.entities.Payment;

public interface PaymentCommand {
  Payment makePayment (Payment payment);
}
