package com.appointment_service.service;

import com.appointment_service.entities.AppointmentStatus;
import com.appointment_service.mapper.AppointmentMapper;
import com.appointment_service.repos.AppointmentRepository;
import com.common.response.AppointmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE)
public class AppointmentActivitiesService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper = AppointmentMapper.INSTANCE;

    public AppointmentResponse getAppointment(int id) {

        var appointment= appointmentRepository.findById(id).orElseThrow();
      return   appointmentMapper.appointmentToAppointmentResponse(appointment);


    }
    public void confirmAppointment(int id) {
        var appointment = appointmentRepository.findById(id).orElseThrow();
        appointment.setStatus(AppointmentStatus.Confirmed);
        throw new RuntimeException("SOME ERROR");


    }
    public void cancelAppointment(int id) {
        var appointment = appointmentRepository.findById(id).orElseThrow();
        appointment.setStatus(AppointmentStatus.Canceled);


    }
    public void failedAppointment(int id) {
        var appointment = appointmentRepository.findById(id).orElseThrow();
        appointment.setStatus(AppointmentStatus.Failed);


    }



}
