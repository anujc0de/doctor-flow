package com.appointment_service.workflow;

import com.common.dto.AppointmentDto;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface AppointmentFulfillmentWorkflow {
  @WorkflowMethod
  void bookAppointment(AppointmentDto appointmentDto);
}
