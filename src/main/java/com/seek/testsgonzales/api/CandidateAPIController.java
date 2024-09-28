package com.seek.testsgonzales.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CandidateAPIController implements CandidateAPI {

  private final CandidateAPIDelegate delegate;

  @Autowired
  public CandidateAPIController(CandidateAPIDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public CandidateAPIDelegate getDelegate() {
    return delegate;
  }
}
