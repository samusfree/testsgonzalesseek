package com.seek.testsgonzales.api;

import com.seek.testsgonzales.model.GenericResponse;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class RestExceptionController {

  private final ErrorAttributes errorAttributes;

  public RestExceptionController(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public GenericResponse<Map<String, Object>> processConstraintViolationException(
      WebRequest request) {
    return GenericResponse.<Map<String, Object>>builder().message("Invalid request")
        .data(errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults()))
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public GenericResponse<Void> processException(Exception e) {
    log.error(e.getMessage(), e);
    return GenericResponse.<Void>builder().message(e.getMessage()).build();
  }
}
