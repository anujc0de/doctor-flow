package com.appointment_service.factory;

import com.appointment_service.command.AppointmentCommand;

public interface AppointmentFactory {

  AppointmentCommand getAppointmentCommand();

}
