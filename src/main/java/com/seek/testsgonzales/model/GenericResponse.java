package com.seek.testsgonzales.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class GenericResponse<T> {

  @Schema(name = "success", description = "Success")
  private boolean success;
  @Schema(name = "message", description = "Message")
  private String message;
  @Schema(name = "data", description = "Data")
  private T data;
}
