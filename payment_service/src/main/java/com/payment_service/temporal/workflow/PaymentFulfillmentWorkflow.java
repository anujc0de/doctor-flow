package com.payment_service.temporal.workflow;

import com.common.dto.PaymentDto;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PaymentFulfillmentWorkflow {
  @WorkflowMethod
  void makePayment(PaymentDto paymentDto);
}
