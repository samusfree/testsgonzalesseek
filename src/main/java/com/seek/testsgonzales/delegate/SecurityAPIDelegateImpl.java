package com.seek.testsgonzales.delegate;

import com.seek.testsgonzales.api.SecurityAPIDelegate;
import com.seek.testsgonzales.model.GenericResponse;
import com.seek.testsgonzales.model.LoginRequest;
import com.seek.testsgonzales.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class SecurityAPIDelegateImpl implements SecurityAPIDelegate {

  private final AuthenticationManager authenticationManager;
  private final JWTUtil jwtUtil;

  public SecurityAPIDelegateImpl(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public ResponseEntity<GenericResponse<String>> login(LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.user(), loginRequest.password())
      );
    } catch (AuthenticationException e) {
      throw new RuntimeException("Incorrect username or password", e);
    }

    final String jwt = jwtUtil.generateToken(loginRequest.user());

    return ResponseEntity.ok(GenericResponse.<String>builder().success(true).data(jwt).build());
  }
}
