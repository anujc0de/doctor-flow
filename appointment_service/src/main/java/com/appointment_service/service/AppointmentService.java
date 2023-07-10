package com.appointment_service.service;


import com.appointment_service.entities.Appointment;
import com.appointment_service.entities.AppointmentStatus;
import com.appointment_service.imp.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE)
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final WebClient.Builder webClientBuilder;

    public Appointment bookAppointment(Appointment appointment) {

        webClientBuilder.build().get()
                .uri("http://localhost:8084/slots/{slotId}/check-and-block", appointment.getSlotId())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        appointment.setStatus(AppointmentStatus.Started);

        return appointmentRepository.save(appointment);


    }

    public Appointment getAppointment(int id) {

        return appointmentRepository.findById(id).orElseThrow();


    }

    public Appointment confirmAppointment(int id) {
        var appointment = appointmentRepository.findById(id).orElseThrow();
        appointment.setStatus(AppointmentStatus.Confirmed);
        return appointment;


    }

}
