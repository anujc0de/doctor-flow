package com.appointment_service.workflow.impl;

import com.appointment_service.workflow.AppointmentFulfillmentWorkflow;
import com.common.TaskQueue;
import com.common.activities.AppointmentActivities;
import com.common.activities.SlotActivities;
import com.common.dto.AppointmentDto;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.failure.ApplicationFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class AppointmentFulfillmentWorkflowImpl implements AppointmentFulfillmentWorkflow {

    private final Logger logger = Workflow.getLogger(this.getClass().getName());

    private final ActivityOptions slotActivityOptions =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(1))
                    .setTaskQueue(TaskQueue.SLOT_ACTIVITY_TASK_QUEUE.name())
//                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
                    .build();


    private final ActivityOptions appointmentActivityOptions =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(1))
                    .setTaskQueue(TaskQueue.APPOINTMENT_ACTIVITY_TASK_QUERY.name())
                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
                    .build();


    private final SlotActivities slotActivities =
            Workflow.newActivityStub(SlotActivities.class, slotActivityOptions);

    private final AppointmentActivities appointmentActivities =
            Workflow.newActivityStub(AppointmentActivities.class, appointmentActivityOptions);


    @Override
    public void bookAppointment(AppointmentDto appointmentDto) {
        // Configure SAGA to run compensation activities in parallel
        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(false).build();
        Saga saga = new Saga(sagaOptions);
        try {
            saga.addCompensation(appointmentActivities::failedAppointment, appointmentDto);
            slotActivities.checkAndBlock(appointmentDto.getSlotId());
            saga.addCompensation(slotActivities::unblock, appointmentDto.getSlotId());


        } catch (ActivityFailure e) {
            Throwable cause = e.getCause();
            if (cause instanceof ApplicationFailure applicationFailure) {
                logger.info("Failed becasue of " + applicationFailure.getOriginalMessage());
                logger.info("cause.getMessage " + cause.getMessage());
            } else {
                logger.info("cause is not instance of ApplicationFailure");
            }
            // we catch our exception and trigger workflow compensation
            saga.compensate();
            throw e;
        }
    }
}
