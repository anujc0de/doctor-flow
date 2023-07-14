package com.slot_service.config;


import com.common.activities.SlotActivities;
import com.slot_service.impl.SlotActivitiesImpl;
import com.slot_service.orchestrator.WorkflowOrchestratorClient;
import com.slot_service.services.SlotActivitiesService;
import com.slot_service.worker.SlotWorker;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class SlotServiceAppConfig {

  @Bean
  public SlotWorker slotWorker(SlotActivitiesService slotActivitiesService) {
    return new SlotWorker(getSlotActivity(slotActivitiesService), workflowOrchestratorClient());
  }

  @Bean
  public SlotActivities getSlotActivity(
          SlotActivitiesService slotActivitiesService) {
    return new SlotActivitiesImpl(slotActivitiesService);
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


}
