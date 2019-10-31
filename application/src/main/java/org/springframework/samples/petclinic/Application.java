package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.springframework.samples.petclinic")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}