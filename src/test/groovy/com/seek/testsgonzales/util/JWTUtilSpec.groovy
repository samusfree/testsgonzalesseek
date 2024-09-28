package com.seek.testsgonzales.util


import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import spock.lang.Specification
import spock.lang.Subject

class JWTUtilSpec extends Specification {
  @Subject
  JWTUtil jwtUtil

  def secret = "secret"
  def expiration = 3600000

  def setup() {
    jwtUtil = new JWTUtil(secret, expiration)
  }

  def "should extract username from token"() {
    given:
    String token = Jwts.builder()
        .setSubject("testUser")
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact()

    when:
    String username = jwtUtil.extractUsername(token)

    then:
    username == "testUser"
  }

  def "should generate a valid token"() {
    when:
    String token = jwtUtil.generateToken("testUser")

    then:
    token != null
    jwtUtil.extractUsername(token) == "testUser"
  }

  def "should validate token"() {
    given:
    String token = jwtUtil.generateToken("testUser")

    when:
    Boolean isValid = jwtUtil.isValidToken(token)

    then:
    isValid == true
  }

  def "should detect expired token"() {
    given:
    jwtUtil = new JWTUtil(secret, -1000)
    String token = jwtUtil.generateToken("testUser")

    when:
    Boolean isValid = jwtUtil.isValidToken(token)

    then:
    isValid == false
  }

  def "should detect token without username"() {
    given:
    String token = jwtUtil.generateToken("")

    when:
    Boolean isValid = jwtUtil.isValidToken(token)

    then:
    isValid == false
  }
}
