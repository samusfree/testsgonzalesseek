package com.seek.testsgonzales.api

import com.seek.testsgonzales.model.GenericResponse
import com.seek.testsgonzales.model.LoginRequest
import com.seek.testsgonzales.util.BaseIntegrationSpec
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SecurityAPIIntegrationSpec extends BaseIntegrationSpec {

  @SuppressWarnings('GroovyAssignabilityCheck')
  def 'test login failed'() {
    given:
    def loginRequest = new LoginRequest("invalid", "invalid")

    when:
    ResponseEntity<GenericResponse<Void>> response = this.testRestTemplate.postForEntity("/api/v1/login", loginRequest, GenericResponse<Void>.class)

    then:
    response.statusCode.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()
    !response.body.success
    response.body.data == null
    response.body.message == "Incorrect username or password"
  }
}
