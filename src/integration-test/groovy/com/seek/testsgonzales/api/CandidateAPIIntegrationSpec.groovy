//file:noinspection GrEqualsBetweenInconvertibleTypes
//file:noinspection GroovyAssignabilityCheck
package com.seek.testsgonzales.api


import com.seek.testsgonzales.model.CandidateDTO
import com.seek.testsgonzales.model.GenericResponse
import com.seek.testsgonzales.model.LoginRequest
import com.seek.testsgonzales.repository.CandidateRepository
import com.seek.testsgonzales.util.BaseIntegrationSpec
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import static org.springframework.http.HttpMethod.DELETE
import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.HttpMethod.PUT

class CandidateAPIIntegrationSpec extends BaseIntegrationSpec {
  @Autowired
  CandidateRepository candidateRepository

  def 'test get candidate by id'() {
    given:
    def entity = createEntity(createToken())

    when:
    ResponseEntity<GenericResponse<CandidateDTO>> response = this.testRestTemplate
        .exchange("/api/v1/candidate/1", GET, entity, GenericResponse<CandidateDTO>.class)

    then:
    response.statusCode.value() == HttpStatus.OK.value()
    response.body.success
    response.body.data.id == 1
    response.body.data.name == "Samuel"
    response.body.data.lastName == "Gonzales"
    response.body.data.email == "samuel.gonzales@test.com"
    response.body.data.gender == "M"
  }

  def 'test get candidate by id that not exists'() {
    given:
    def entity = createEntity(createToken())

    when:
    ResponseEntity<GenericResponse<Void>> response = this.testRestTemplate
        .exchange("/api/v1/candidate/1000", GET, entity, GenericResponse<Void>.class)

    then:
    response.statusCode.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()
    !response.body.success
    response.body.message == "Candidate not found"
  }

  def 'test get all candidates'() {
    given:
    def entity = createEntity(createToken())

    when:
    ResponseEntity<GenericResponse<List<CandidateDTO>>> response = this.testRestTemplate
        .exchange("/api/v1/candidate", GET, entity, GenericResponse<List<CandidateDTO>>.class)

    then:
    response.statusCode.value() == HttpStatus.OK.value()
    response.body.success
    response.body.data.size() == 5
    response.body.data[0].id == 1
    response.body.data[0].name == "Samuel"
    response.body.data[0].lastName == "Gonzales"
    response.body.data[0].email == "samuel.gonzales@test.com"
    response.body.data[0].gender == "M"
  }

  def 'test get all candidates with no token'() {
    when:
    ResponseEntity<GenericResponse<List<CandidateDTO>>> response = this.testRestTemplate
        .exchange("/api/v1/candidate", GET, createEntity("Invalid"), GenericResponse<List<CandidateDTO>>.class)

    then:
    response.statusCode.value() == HttpStatus.FORBIDDEN.value()
  }

  def 'test get all candidates with invalid token type'() {
    when:
    ResponseEntity<GenericResponse<List<CandidateDTO>>> response = this.testRestTemplate
        .exchange("/api/v1/candidate", GET, createInvalidBearer(StringUtils.EMPTY), GenericResponse<List<CandidateDTO>>.class)

    then:
    response.statusCode.value() == HttpStatus.FORBIDDEN.value()
  }

  def 'test create candidate'() {
    given:
    def entity = createEntity(createCandidateDTO(), createToken())

    when:
    ResponseEntity<GenericResponse<CandidateDTO>> response = this.testRestTemplate
        .exchange("/api/v1/candidate", POST, entity, GenericResponse<CandidateDTO>.class)

    then:
    response.statusCode.value() == HttpStatus.CREATED.value()
    response.body.success
    response.body.data.id == 6
    response.body.data.name == "Samuel"
    response.body.data.lastName == "Gonzales"
    response.body.data.email == "sagonzales89@gmail.com"
    response.body.data.gender == "M"
  }

  def 'test create already exists'() {
    given:
    def entity = createEntity(new CandidateDTO(email: "samuel.gonzales@test.com"), createToken())

    when:
    ResponseEntity<GenericResponse<CandidateDTO>> response = this.testRestTemplate
        .exchange("/api/v1/candidate", POST, entity, GenericResponse<CandidateDTO>.class)

    then:
    response.statusCode.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()
    !response.body.success
    response.body.message == "Email already exists"
  }

  def 'test update candidate'() {
    given:
    def candidateDTO = createCandidateDTO()
    candidateDTO.id = 1
    candidateDTO.email = "sagonzales89@gmail.com"
    candidateDTO.salaryExpected = 12000

    when:
    ResponseEntity<GenericResponse<CandidateDTO>> response = this.testRestTemplate
        .exchange("/api/v1/candidate/1", PUT, new HttpEntity<>(candidateDTO), GenericResponse<CandidateDTO>.class)

    then:
    response.statusCode.value() == HttpStatus.OK.value()
    response.body.success
    response.body.data.email == "sagonzales89@gmail.com"
    response.body.data.salaryExpected == 12000
  }

  def 'test update candidate that not exists'() {
    given:
    def candidateDTO = createCandidateDTO()
    candidateDTO.id = 1

    when:
    ResponseEntity<GenericResponse<Void>> response = this.testRestTemplate
        .exchange("/api/v1/candidate/1000", PUT, new HttpEntity<>(candidateDTO), GenericResponse<Void>.class)

    then:
    response.statusCode.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()
    !response.body.success
    response.body.message == "Candidate not found"
  }

  def 'test delete candidate that not exists'() {
    when:
    ResponseEntity<GenericResponse<Void>> response = this.testRestTemplate
        .exchange("/api/v1/candidate/1000", DELETE, null, GenericResponse<Void>.class)

    then:
    response.statusCode.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()
    !response.body.success
    response.body.message == "Candidate not found"
  }

  def 'test delete candidate'() {
    when:
    ResponseEntity<GenericResponse<Void>> response = this.testRestTemplate
        .exchange("/api/v1/candidate/1", DELETE, null, GenericResponse<Void>.class)
    ResponseEntity<GenericResponse<Void>> responseGet = this.testRestTemplate
        .exchange("/api/v1/candidate/1", GET, createEntity(createToken()), GenericResponse<Void>.class)

    then:
    response.statusCode.value() == HttpStatus.OK.value()
    response.body.success
    responseGet.statusCode.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()
    !responseGet.body.success
    responseGet.body.message == "Candidate not found"
  }

  private static HttpEntity createEntity(String token) {
    HttpHeaders headers = new HttpHeaders()
    headers.setBearerAuth(token)
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
    return new HttpEntity<>(headers)
  }

  private static HttpEntity createInvalidBearer(String token) {
    HttpHeaders headers = new HttpHeaders()
    headers.set(HttpHeaders.AUTHORIZATION, "Invalid " + token)
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

  private static CandidateDTO createCandidateDTO() {
    return new CandidateDTO(name: "Samuel", lastName: "Gonzales",
        email: "sagonzales89@gmail.com", gender: "M", salaryExpected: 5000, lastApply: new Date())
  }
}
