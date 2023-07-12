package com.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Setter
@Getter
@ToString
public class PaymentDto {
    private int id;
    private int appointmentId;
    private String paymentStatus;
    private Instant createdAt;
    private Instant updatedAt;
}
