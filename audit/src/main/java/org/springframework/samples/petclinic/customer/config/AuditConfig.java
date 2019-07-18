package org.springframework.samples.petclinic.customer.config;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.customer.listener.AuditEventListener;

@Configuration
public class AuditConfig {
  @Bean
  public AuditEventListener auditEventListener(EventBus eventBus) {
    AuditEventListener auditEventListener = new AuditEventListener();
    eventBus.register(auditEventListener);
    return auditEventListener;
  }
}