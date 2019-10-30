package org.springframework.samples.petclinic.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ComponentScan("org.springframework.samples.petclinic")
public class ApplicationConfig {
}
