package com.appointment_service.command;


import com.appointment_service.entities.Appointment;

public interface AppointmentCommand {
  Appointment bookAppointment (Appointment appointment);
}
