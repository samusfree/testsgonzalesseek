package com.seek.testsgonzales.api;

import com.seek.testsgonzales.model.GenericResponse;
import com.seek.testsgonzales.model.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "security", description = "Security API")
public interface SecurityAPI {

  SecurityAPIDelegate getDelegate();

  /**
   * POST /login : login
   *
   * @param loginRequest request with username and password (required)
   * @return login was successfully returned (status code 200) or Invalid username or password
   */
  @Operation(summary = "Login", description = "Login", tags = {
      "security"})
  @PostMapping(value = "/login", consumes = {"application/json"}, produces = {
      "application/json"})
  default ResponseEntity<GenericResponse<String>> login(
      @Parameter(description = "Candidate Information", required = true)
      @RequestBody
      LoginRequest loginRequest) {
    return getDelegate().login(loginRequest);
  }
}
