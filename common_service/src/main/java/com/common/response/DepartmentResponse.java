package com.common.response;

import com.common.dto.DoctorDto;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class DepartmentResponse {
    private int id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;
    private Set<DoctorDto> doctors;
}
