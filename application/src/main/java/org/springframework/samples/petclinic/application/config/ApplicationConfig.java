package org.springframework.samples.petclinic.application.config;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.samples.petclinic.customer.config.AuditConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@Import(AuditConfig.class)
@ComponentScan("org.springframework.samples.petclinic")
public class ApplicationConfig {

  @Bean
  public EventBus eventBus() {
    return new EventBus();
  }
}
