package com.common.activities;

import com.common.dto.AppointmentDto;
import com.common.dto.PaymentDto;
import com.common.response.AppointmentResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AppointmentActivities {
    @ActivityMethod
    AppointmentResponse getAppointment(PaymentDto paymentDto);
    @ActivityMethod
    void completeAppointment(PaymentDto payment);
    @ActivityMethod
    void cancelAppointment(PaymentDto payment);
    @ActivityMethod
    void failedAppointment(AppointmentDto appointmentDto);
}
