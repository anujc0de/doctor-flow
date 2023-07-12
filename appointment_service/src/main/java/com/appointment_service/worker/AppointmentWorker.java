package com.appointment_service.worker;

import com.appointment_service.orchestrator.WorkflowOrchestratorClient;
import com.appointment_service.workflow.impl.AppointmentFulfillmentWorkflowImpl;
import com.common.TaskQueue;
import com.common.activities.AppointmentActivities;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class AppointmentWorker {

  private final AppointmentActivities appointmentActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering appointment worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    WorkflowImplementationOptions workflowImplementationOptions =
            WorkflowImplementationOptions.newBuilder()
                    .setFailWorkflowExceptionTypes(NullPointerException.class)
                    .build();
    var workerFactory = WorkerFactory.newInstance(workflowClient);

    var worker =
        workerFactory.newWorker(TaskQueue.APPOINTMENT_ACTIVITY_TASK_QUERY.name(), workerOptions);
    worker.registerWorkflowImplementationTypes(
            workflowImplementationOptions, AppointmentFulfillmentWorkflowImpl.class);

    worker.registerActivitiesImplementations(appointmentActivities);

    workerFactory.start();

    log.info("appointment worker started..");
  }
}
