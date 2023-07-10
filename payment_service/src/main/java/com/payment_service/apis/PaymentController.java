package com.payment_service.apis;

import com.payment_service.mapper.PaymentMapper;
import com.payment_service.request.PaymentRequest;
import com.payment_service.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody PaymentRequest paymentRequest) {

        var payment=paymentMapper.paymenttRequestToPayment(paymentRequest);
        return new ResponseEntity<>(paymentService.makePayment(payment), HttpStatus.CREATED);


    }
}
