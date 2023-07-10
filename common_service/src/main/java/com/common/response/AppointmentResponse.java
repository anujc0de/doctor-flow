package com.common.response;


import lombok.Data;

import java.time.Instant;

@Data
public class AppointmentResponse {
    private int id;
    private int userId;

    private int slotId;

    private String status;

    private Instant createdAt;

    private Instant updatedAt;
}
