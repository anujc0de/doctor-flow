package com.common.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Setter
@Getter
@ToString
public class AppointmentDto {
    private int id;
    private int userId;

    private int slotId;

    private String status;

    private Instant createdAt;

    private Instant updatedAt;
}
