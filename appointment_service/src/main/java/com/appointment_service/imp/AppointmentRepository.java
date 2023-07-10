package com.appointment_service.imp;

import com.appointment_service.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
