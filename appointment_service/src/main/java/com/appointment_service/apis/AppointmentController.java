package com.appointment_service.apis;


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

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable("id") int id) {

        var appointment = appointmentService.getAppointment(id);
        return new ResponseEntity<>(appointmentMapper.appointmentToAppointmentResponse(appointment), HttpStatus.CREATED);


    }

    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequest appointmentRequest) {

        var appointment = appointmentMapper.appointmentRequestToAppointment(appointmentRequest);

        return new ResponseEntity<>(appointmentService.bookAppointment(appointment), HttpStatus.CREATED);


    }

    @PostMapping("{id}/confirm")
    public ResponseEntity<?> confirmAppointment(@PathVariable("id") int id) {


        return new ResponseEntity<>(appointmentService.confirmAppointment(id), HttpStatus.CREATED);


    }


}
