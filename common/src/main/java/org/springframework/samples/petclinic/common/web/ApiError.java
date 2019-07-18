package org.springframework.samples.petclinic.common.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiError {
  private final int status;
  private final String title;
  private final String detail;
}
