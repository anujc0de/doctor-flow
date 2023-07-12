package com.payment_service.command.impl;

import com.payment_service.command.PaymentCommand;
import com.payment_service.entities.Payment;
import com.payment_service.entities.PaymentStatus;
import com.payment_service.imp.PaymentRepository;
import com.payment_service.orchestration.WorkflowOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PaymentCommandImpl implements PaymentCommand {

    private final PaymentRepository paymentRepository;
    private final WorkflowOrchestrator workflowOrchestrator;


    @Override
    public Payment makePayment(Payment payment) {

        payment.setPaymentStatus(PaymentStatus.Started);
        var persistedPayment = paymentRepository.save(payment);
        workflowOrchestrator.makePayment(persistedPayment);
        return persistedPayment;
    }
}
