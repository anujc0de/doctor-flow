package com.payment_service.temporal.orchestrator;

import com.common.TaskQueue;
import com.common.dto.PaymentDto;
import com.payment_service.config.ApplicationProperties;
import com.payment_service.entities.Payment;
import com.payment_service.orchestration.WorkflowOrchestrator;
import com.payment_service.temporal.workflow.PaymentFulfillmentWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {

  private final WorkflowOrchestratorClient workflowOrchestratorClient;
  private final ApplicationProperties applicationProperties;

  @Override
  public void makePayment(Payment payment) {

    var paymentDto = map(payment);

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();
    var paymentFulfillmentWorkflow =
        workflowClient.newWorkflowStub(
            PaymentFulfillmentWorkflow.class,
            WorkflowOptions.newBuilder()
                .setWorkflowId(applicationProperties.getWorkflowId() + "-" + payment.getId())
                    .setWorkflowExecutionTimeout(Duration.ofMinutes(2))
                .setTaskQueue(TaskQueue.PAYMENT_FULFILLMENT_WORKFLOW_TASK_QUEUE.name())
                .build());
    // Execute Sync
    //    orderFulfillmentWorkflow.createOrder(orderDTO);
    // Async execution
    WorkflowClient.start(paymentFulfillmentWorkflow::makePayment, paymentDto);
  }

  private PaymentDto map(Payment payment) {
    var paymentDTO = new PaymentDto();
    paymentDTO.setId(payment.getId());
    paymentDTO.setPaymentStatus(payment.getPaymentStatus());
    paymentDTO.setAppointmentId(payment.getAppointmentId());
    return paymentDTO;
  }


}
