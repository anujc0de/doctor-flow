package com.payment_service.services;

import com.common.response.AppointmentResponse;
import com.payment_service.entities.Payment;
import com.payment_service.entities.PaymentStatus;
import com.payment_service.imp.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final WebClient.Builder webClientBuilder;


    public Payment makePayment(Payment payment) {


        //get appointment details

        AppointmentResponse appointmentResponse = webClientBuilder.build().get()
                .uri("http://localhost:8081/appointments/{id}", payment.getAppointmentId())
                .retrieve()
                .bodyToMono(AppointmentResponse.class)
                .block();

        assert appointmentResponse != null;

        //mark slot is confirmed`
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8084/slots/{id}/confirm", appointmentResponse.getSlotId())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        //mark appointment confirmed
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/appointments/{id}/confirm", payment.getAppointmentId())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //create payament

        payment.setPaymentStatus(PaymentStatus.Completed);
       return paymentRepository.save(payment);

        //appointment comfirmend


    }


}
