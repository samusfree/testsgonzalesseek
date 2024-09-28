package com.seek.testsgonzales.delegate;

import com.seek.testsgonzales.api.SecurityAPIDelegate;
import com.seek.testsgonzales.model.GenericResponse;
import com.seek.testsgonzales.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SecurityAPIDelegateImpl implements SecurityAPIDelegate {

  @Override
  public ResponseEntity<GenericResponse<String>> login(LoginRequest loginRequest) {
    return null;
  }
}
