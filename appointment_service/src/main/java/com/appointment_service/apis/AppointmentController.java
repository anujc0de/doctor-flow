package com.appointment_service.apis;


import com.appointment_service.factory.AppointmentFactory;
import com.appointment_service.mapper.AppointmentMapper;
import com.appointment_service.request.AppointmentRequest;
import com.appointment_service.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("appointments")
public class AppointmentController {
    private final AppointmentMapper appointmentMapper = AppointmentMapper.INSTANCE;

    private final AppointmentService appointmentService;
    private  final AppointmentFactory appointmentFactory;

    public AppointmentController(AppointmentService appointmentService, AppointmentFactory appointmentFactory) {
        this.appointmentService = appointmentService;
        this.appointmentFactory = appointmentFactory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable("id") int id) {

        var appointment = appointmentService.getAppointment(id);
        return new ResponseEntity<>(appointmentMapper.appointmentToAppointmentResponse(appointment), HttpStatus.CREATED);


    }

    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequest appointmentRequest) {


        var appointmentCommand =appointmentFactory.getAppointmentCommand();
        var appointment = appointmentMapper.appointmentRequestToAppointment(appointmentRequest);
        var pendingAppointment=appointmentCommand.bookAppointment(appointment);


        return new ResponseEntity<>(pendingAppointment, HttpStatus.CREATED);


    }

    @PostMapping("{id}/confirm")
    public ResponseEntity<?> confirmAppointment(@PathVariable("id") int id) {


        return new ResponseEntity<>(appointmentService.confirmAppointment(id), HttpStatus.CREATED);


    }


}
