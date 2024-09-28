package com.seek.testsgonzales.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SecurityAPIController implements SecurityAPI {

  private final SecurityAPIDelegate delegate;

  @Autowired
  public SecurityAPIController(SecurityAPIDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public SecurityAPIDelegate getDelegate() {
    return delegate;
  }
}
