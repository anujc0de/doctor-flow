package com.appointment_service.mapper;

import com.appointment_service.entities.Appointment;
import com.appointment_service.request.AppointmentRequest;
import com.common.response.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);


    Appointment appointmentRequestToAppointment(AppointmentRequest appointmentRequest);
    AppointmentResponse appointmentToAppointmentResponse(Appointment appointment);
}
