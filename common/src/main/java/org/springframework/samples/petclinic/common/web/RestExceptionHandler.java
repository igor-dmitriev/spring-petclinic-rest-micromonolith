package org.springframework.samples.petclinic.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.common.error.VisitsAmountIsExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

  private static final String ACTION_NOT_ALLOWED_MESSAGE = "Action is not allowed";

  @ResponseBody
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(value = {VisitsAmountIsExceededException.class})
  protected ApiErrors visitAmountIsExceeded(VisitsAmountIsExceededException ex) {
    log.warn(ex.getMessage(), ex);
    return ApiErrors.create(
        HttpStatus.UNPROCESSABLE_ENTITY,
        ACTION_NOT_ALLOWED_MESSAGE,
        ex.getMessage()
    );
  }
}