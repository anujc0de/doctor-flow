package com.appointment_service.factory.impl;

import com.appointment_service.command.AppointmentCommand;
import com.appointment_service.factory.AppointmentFactory;

public class AppointmentFactoryImpl implements AppointmentFactory {

  private final AppointmentCommand appointmentCommand;

  public AppointmentFactoryImpl(AppointmentCommand appointmentCommand) {
    this.appointmentCommand = appointmentCommand;
  }




  @Override
  public AppointmentCommand getAppointmentCommand() {
    return this.appointmentCommand;
  }
}
