//file:noinspection GroovyAccessibility
package com.seek.testsgonzales.secutiry.filter

import com.seek.testsgonzales.security.filter.JWTRequestFilter
import com.seek.testsgonzales.util.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class JWTRequestFilterSpec extends Specification {
  @Subject
  JWTRequestFilter jwtRequestFilter

  JWTUtil jwtUtil = Mock()
  HttpServletRequest request = Mock()
  HttpServletResponse response = Mock()
  FilterChain filterChain = Mock()

  def setup() {
    jwtRequestFilter = new JWTRequestFilter(jwtUtil)
  }

  @Unroll
  def "should not set authentication context if token is invalid"() {
    given:
    SecurityContextHolder.context.authentication = null
    String token = "invalidToken"
    request.getHeader("Authorization") >> "Bearer $token"
    jwtUtil.isValidToken(token) >> false

    when:
    jwtRequestFilter.doFilterInternal request, response, filterChain

    then:
    1 * filterChain.doFilter(request, response)
    SecurityContextHolder.context.authentication == null
  }

  @Unroll
  def "should not set authentication context if no authorization header"() {
    given:
    SecurityContextHolder.context.authentication = null
    request.getHeader("Authorization") >> null

    when:
    jwtRequestFilter.doFilterInternal(request, response, filterChain)

    then:
    1 * filterChain.doFilter(request, response)
    SecurityContextHolder.context.authentication == null
  }

  def "should set authentication context if token is valid"() {
    given:
    SecurityContextHolder.context.authentication = null
    String token = "validToken"
    request.getHeader("Authorization") >> "Bearer $token"
    jwtUtil.isValidToken(token) >> true
    jwtUtil.extractUsername(token) >> "testUser"

    when:
    jwtRequestFilter.doFilterInternal(request, response, filterChain)

    then:
    1 * filterChain.doFilter(request, response)
    SecurityContextHolder.getContext().authentication.name == "testUser"
  }

  def "should set authentication context if token dont start with Bearer"() {
    given:
    SecurityContextHolder.context.authentication = null
    String token = "validToken"
    request.getHeader("Authorization") >> "Invalid $token"
    jwtUtil.isValidToken(token) >> true
    jwtUtil.extractUsername(token) >> "testUser"

    when:
    jwtRequestFilter.doFilterInternal(request, response, filterChain)

    then:
    1 * filterChain.doFilter(request, response)
    SecurityContextHolder.context.authentication == null
  }
}
