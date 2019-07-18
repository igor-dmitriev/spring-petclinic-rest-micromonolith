package org.springframework.samples.petclinic.common.web;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiErrors {
  private final List<ApiError> errors;

  public static ApiErrors create(HttpStatus errorCode, String title, String message) {
    return new ApiErrors(Collections.singletonList(new ApiError(errorCode.value(), title, message)));
  }
}
