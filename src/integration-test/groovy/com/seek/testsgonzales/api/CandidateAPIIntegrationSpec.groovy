//file:noinspection GrEqualsBetweenInconvertibleTypes
//file:noinspection GroovyAssignabilityCheck
package com.seek.testsgonzales.api


import com.seek.testsgonzales.model.CandidateDTO
import com.seek.testsgonzales.model.GenericResponse
import com.seek.testsgonzales.model.LoginRequest
import com.seek.testsgonzales.repository.CandidateRepository
import com.seek.testsgonzales.util.BaseIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import static org.springframework.http.HttpMethod.GET

class CandidateAPIIntegrationSpec extends BaseIntegrationSpec {
  @Autowired
  CandidateRepository candidateRepository

  def setup() {
  }

  def 'test get candidate by id'() {
    given:
    def entity = createEntity(createToken())

    when:
    ResponseEntity<GenericResponse<CandidateDTO>> response = this.testRestTemplate
        .exchange("/api/v1/candidate/1", GET, entity, GenericResponse<CandidateDTO>.class)

    then:
    response.statusCode.value() == 200
    response.body.success
    response.body.data.id == 1
    response.body.data.name == "Samuel"
    response.body.data.lastName == "Gonzales"
    response.body.data.email == "samuel.gonzales@test.com"
    response.body.data.gender == "M"
  }

  def 'test get all candidates'() {
    given:
    def entity = createEntity(createToken())

    when:
    ResponseEntity<GenericResponse<List<CandidateDTO>>> response = this.testRestTemplate
        .exchange("/api/v1/candidate", GET, entity, GenericResponse<List<CandidateDTO>>.class)

    then:
    response.statusCode.value() == 200
    response.body.success
    response.body.data.size() == 5
    response.body.data[0].id == 1
    response.body.data[0].name == "Samuel"
    response.body.data[0].lastName == "Gonzales"
    response.body.data[0].email == "samuel.gonzales@test.com"
    response.body.data[0].gender == "M"
  }

  private static HttpEntity createEntity(String token) {
    HttpHeaders headers = new HttpHeaders()
    headers.setBearerAuth(token)
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
    return new HttpEntity<>(headers)
  }

  private static <T> HttpEntity<T> createEntity(T body, String token) {
    HttpHeaders headers = new HttpHeaders()
    headers.setBearerAuth(token)
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
    return new HttpEntity<T>(body, headers)
  }

  private String createToken() {
    def loginRequest = new LoginRequest("seek", "seek")
    return this.testRestTemplate.postForEntity("/api/v1/login", loginRequest, GenericResponse<String>.class).body.data
  }
}
