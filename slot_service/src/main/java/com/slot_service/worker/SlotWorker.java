package com.slot_service.worker;

import com.common.TaskQueue;
import com.common.activities.SlotActivities;
import com.slot_service.orchestrator.WorkflowOrchestratorClient;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class SlotWorker {

  private final SlotActivities slotActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering slot worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.SLOT_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    worker.registerActivitiesImplementations(slotActivities);

    workerFactory.start();

    log.info("worker slot started..");
  }
}
