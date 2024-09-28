package com.seek.testsgonzales.api;

import com.seek.testsgonzales.model.GenericResponse;
import com.seek.testsgonzales.model.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface SecurityAPIDelegate {

  /**
   * POST /login : login
   *
   * @param loginRequest loginRequest
   * @return login was successfully returned (status code 200) or Invalid username or password
   * @see SecurityAPI#login
   */
  ResponseEntity<GenericResponse<String>> login(LoginRequest loginRequest);
}
