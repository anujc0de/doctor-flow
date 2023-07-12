package com.appointment_service.orchestration;


import com.appointment_service.entities.Appointment;

public interface WorkflowOrchestrator {
  void bookAppointment(Appointment appointment);
}
