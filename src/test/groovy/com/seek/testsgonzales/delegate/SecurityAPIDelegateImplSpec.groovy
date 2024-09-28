package com.seek.testsgonzales.delegate

import com.seek.testsgonzales.api.SecurityAPIDelegate
import com.seek.testsgonzales.model.LoginRequest
import com.seek.testsgonzales.util.JWTUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import spock.lang.Specification
import spock.lang.Subject

class SecurityAPIDelegateImplSpec extends Specification {
  @Subject
  SecurityAPIDelegate securityAPIDelegate
  AuthenticationManager authenticationManager
  JWTUtil jwtUtil

  def setup() {
    authenticationManager = Mock()
    jwtUtil = Mock()
    securityAPIDelegate = new SecurityAPIDelegateImpl(authenticationManager, jwtUtil)
  }

  def "test login"() {
    given:
    def username = "test"
    def password = "password"
    def token = "token"
    authenticationManager.authenticate(_ as Authentication) >> null
    jwtUtil.generateToken(_ as String) >> token

    when:
    def securityResponse = securityAPIDelegate.login(new LoginRequest(username, password))

    then:
    securityResponse != null
    securityResponse.statusCode.value() == 200
    securityResponse.body.success
    securityResponse.body.data == token
  }

  def "test login return invalid authentication"() {
    given:
    def username = "test"
    def password = "password"
    authenticationManager.authenticate(_ as Authentication) >> { throw new BadCredentialsException("Invalid") }

    when:
    securityAPIDelegate.login(new LoginRequest(username, password))

    then:
    def exception = thrown(RuntimeException.class)
    exception.message == "Incorrect username or password"
  }
}
