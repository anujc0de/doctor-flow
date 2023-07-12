package com.appointment_service.config;


import com.appointment_service.command.AppointmentCommand;
import com.appointment_service.command.impl.AppointmentCommandImpl;
import com.appointment_service.factory.AppointmentFactory;
import com.appointment_service.factory.impl.AppointmentFactoryImpl;
import com.appointment_service.orchestration.WorkflowOrchestrator;
import com.appointment_service.orchestrator.WorkflowOrchestratorImpl;
import com.appointment_service.repos.AppointmentRepository;
import com.appointment_service.workflow.activity.impl.AppointmentActivitiesImpl;
import com.appointment_service.orchestrator.WorkflowOrchestratorClient;
import com.appointment_service.service.AppointmentActivitiesService;
import com.appointment_service.worker.AppointmentWorker;
import com.common.activities.AppointmentActivities;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppointmentServiceAppConfig {

  @Bean
  public AppointmentWorker appointmentWorker(AppointmentActivitiesService appointmentActivitiesService) {
    return new AppointmentWorker(getAppointmentActivity(appointmentActivitiesService), workflowOrchestratorClient());
  }

  @Bean
  public AppointmentActivities getAppointmentActivity(
          AppointmentActivitiesService appointmentActivitiesService) {
    return new AppointmentActivitiesImpl(appointmentActivitiesService);
  }

  @Bean
  public AppointmentFactory appointmentFactory(AppointmentRepository appointmentRepository) {
    return new AppointmentFactoryImpl(appointmentCommand(appointmentRepository));
  }

  @Bean
  public AppointmentCommand appointmentCommand(AppointmentRepository appointmentRepository) {
    return new AppointmentCommandImpl(appointmentRepository, workflowOrchestrator());
  }


  @Bean
  @ConfigurationProperties
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }

  @Bean
  public WorkflowOrchestratorClient workflowOrchestratorClient() {
    return new WorkflowOrchestratorClient(applicationProperties());
  }
  @Bean
  public WorkflowOrchestrator workflowOrchestrator() {
    return new WorkflowOrchestratorImpl(workflowOrchestratorClient(), applicationProperties());
  }


}
