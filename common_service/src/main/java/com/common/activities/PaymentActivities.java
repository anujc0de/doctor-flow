package com.common.activities;

import com.common.dto.PaymentDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentActivities {
  @ActivityMethod
  void completePayment(PaymentDto payment);
  @ActivityMethod
  void failPayment(PaymentDto payment);
}
