package org.springframework.samples.petclinic.config;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ComponentScan("org.springframework.samples.petclinic")
public class ApplicationConfig {

  @Bean
  public EventBus eventBus() {
    return new EventBus();
  }

 /* @Bean
  public AuditEventListener auditEventListener(EventBus eventBus,
                                               AuditService auditService) {
    AuditEventListener auditEventListener = new AuditEventListener(auditService);
    eventBus.register(auditEventListener);
    return auditEventListener;
  }*/
}
