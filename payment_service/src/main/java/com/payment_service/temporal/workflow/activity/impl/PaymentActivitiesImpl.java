package com.payment_service.temporal.workflow.activity.impl;

import com.common.activities.PaymentActivities;
import com.common.dto.PaymentDto;
import com.payment_service.entities.PaymentStatus;
import com.payment_service.imp.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PaymentActivitiesImpl implements PaymentActivities {

    private final PaymentRepository paymentRepository;


    @Override
    public void completePayment(PaymentDto paymentDto) {

        var payment = paymentRepository.findById(paymentDto.getId()).orElseThrow();
        log.info("Marking payment as completed, payment id {}", paymentDto.getId());
        payment.setPaymentStatus(PaymentStatus.Completed);
        var completePayment = paymentRepository.save(payment);
        log.info("payment completed, {}", completePayment);


    }


    @Override
    public void failPayment(PaymentDto paymentDto) {
        log.info("Marking payment as failed, payment id {}", paymentDto.getId());
        var payment = paymentRepository.findById(paymentDto.getId()).orElseThrow();
        payment.setPaymentStatus(PaymentStatus.Failed);
        var failedPayment = paymentRepository.save(payment);
        log.info("payment failed, {}", failedPayment);

    }



}
