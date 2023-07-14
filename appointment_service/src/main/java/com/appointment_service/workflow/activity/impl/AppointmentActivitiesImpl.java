package com.appointment_service.workflow.activity.impl;

import com.appointment_service.service.AppointmentActivitiesService;
import com.common.activities.AppointmentActivities;
import com.common.dto.AppointmentDto;
import com.common.dto.PaymentDto;
import com.common.response.AppointmentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AppointmentActivitiesImpl implements AppointmentActivities {
    private final AppointmentActivitiesService appointmentActivitiesService;





    @Override
    public AppointmentResponse getAppointment(PaymentDto paymentDto) {
        return appointmentActivitiesService.getAppointment(paymentDto.getAppointmentId());
    }

    @Override
    public void completeAppointment(PaymentDto payment) {
        appointmentActivitiesService.confirmAppointment(payment.getAppointmentId());

    }

    @Override
    public void cancelAppointment(PaymentDto payment) {

        appointmentActivitiesService.cancelAppointment(payment.getAppointmentId());

    }

    @Override
    public void failedAppointment(AppointmentDto appointmentDto) {
        appointmentActivitiesService.failedAppointment(appointmentDto.getId());
    }
}
