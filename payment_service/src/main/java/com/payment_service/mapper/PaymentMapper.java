package com.payment_service.mapper;

import com.payment_service.entities.Payment;
import com.payment_service.request.PaymentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);


    Payment paymenttRequestToPayment(PaymentRequest paymentRequest);
}
