package com.appointment_service.command.impl;

import com.appointment_service.command.AppointmentCommand;
import com.appointment_service.entities.Appointment;
import com.appointment_service.entities.AppointmentStatus;
import com.appointment_service.orchestration.WorkflowOrchestrator;
import com.appointment_service.repos.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AppointmentCommandImpl implements AppointmentCommand {


    private final AppointmentRepository appointmentRepository;
    private final WorkflowOrchestrator workflowOrchestrator;


    @Override
    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.Started);
        var persistedAppointment = appointmentRepository.save(appointment);
        workflowOrchestrator.bookAppointment(persistedAppointment);

        return persistedAppointment;
    }
}
