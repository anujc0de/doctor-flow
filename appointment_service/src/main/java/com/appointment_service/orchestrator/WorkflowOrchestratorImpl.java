package com.appointment_service.orchestrator;

import com.appointment_service.config.ApplicationProperties;
import com.appointment_service.entities.Appointment;
import com.appointment_service.orchestration.WorkflowOrchestrator;
import com.appointment_service.workflow.AppointmentFulfillmentWorkflow;
import com.common.TaskQueue;
import com.common.dto.AppointmentDto;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {

  private final WorkflowOrchestratorClient workflowOrchestratorClient;
  private final ApplicationProperties applicationProperties;






  @Override
  public void bookAppointment(Appointment appointment) {
    var appointmentDto = map(appointment);
    var workflowClient = workflowOrchestratorClient.getWorkflowClient();
    var appointmentFulfillmentWorkflow =
            workflowClient.newWorkflowStub(
                    AppointmentFulfillmentWorkflow.class,
                    WorkflowOptions.newBuilder()
                            .setWorkflowId(applicationProperties.getWorkflowId() + "-" + appointment.getId()+"-23")
                            .setWorkflowExecutionTimeout(Duration.ofMinutes(2))
                            .setTaskQueue(TaskQueue.APPOINTMENT_ACTIVITY_TASK_QUERY.name())
                            .build());
    // Execute Sync
    //    orderFulfillmentWorkflow.createOrder(orderDTO);
    // Async execution
    WorkflowClient.start(appointmentFulfillmentWorkflow::bookAppointment, appointmentDto);

  }

  private AppointmentDto map(Appointment appointment) {
    var appointmentDto = new AppointmentDto();
    appointmentDto.setId(appointment.getId());
    appointmentDto.setStatus(appointment.getStatus());
    appointmentDto.setSlotId(appointment.getSlotId());
    appointmentDto.setUserId(appointment.getUserId());

    return appointmentDto;
  }
}
