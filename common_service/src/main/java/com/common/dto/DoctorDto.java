package com.common.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class DoctorDto {

    private UUID id;

    private String name;
    private String specialization;

    private String email;

    private Instant createdAt;

    private Instant updatedAt;


}
