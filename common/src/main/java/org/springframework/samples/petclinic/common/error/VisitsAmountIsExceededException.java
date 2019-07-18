package org.springframework.samples.petclinic.common.error;

public class VisitsAmountIsExceededException extends RuntimeException {
  public VisitsAmountIsExceededException() {
    super("Visits amount is exceeded");
  }
}