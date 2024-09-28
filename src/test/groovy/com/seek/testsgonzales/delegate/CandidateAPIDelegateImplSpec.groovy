package com.seek.testsgonzales.delegate


import com.seek.testsgonzales.model.CandidateDTO
import com.seek.testsgonzales.service.CandidateService
import spock.lang.Specification
import spock.lang.Subject

class CandidateAPIDelegateImplSpec extends Specification {
  @Subject
  CandidateAPIDelegateImpl candidateAPIDelegate
  CandidateService candidateService
  def candidateId = 1
  def candidateName = "Samuel"
  def candidateLastName = "Gonzales"
  def candidateEmail = "sagonzales89@gmail.com"

  def setup() {
    candidateService = Mock()
    candidateAPIDelegate = new CandidateAPIDelegateImpl(candidateService)
  }

  def "test get candidate by id"() {
    given:
    candidateService.getCandidateById(candidateId) >> createCandidateDTO()

    when:
    def candidateResponse = candidateAPIDelegate.getCandidateById(candidateId)

    then:
    candidateResponse != null
    candidateResponse.statusCode.value() == 200
    candidateResponse.body.success
    candidateResponse.body.data.name == candidateName
    candidateResponse.body.data.lastName == candidateLastName
    candidateResponse.body.data.email == candidateEmail
  }

  def "test create candidate"() {
    given:
    candidateService.createCandidate(_ as CandidateDTO) >> createCandidateDTO()

    when:
    def candidateResponse = candidateAPIDelegate.createCandidate(createCandidateDTO())

    then:
    candidateResponse != null
    candidateResponse.statusCode.value() == 201
    candidateResponse.body.success
    candidateResponse.body.data.name == candidateName
    candidateResponse.body.data.lastName == candidateLastName
    candidateResponse.body.data.email == candidateEmail
  }

  def "test update candidate"() {
    given:
    candidateService.updateCandidate(candidateId, _ as CandidateDTO) >> createCandidateDTO()

    when:
    def candidateResponse = candidateAPIDelegate.updateCandidate(candidateId, createCandidateDTO())

    then:
    candidateResponse != null
    candidateResponse.statusCode.value() == 200
    candidateResponse.body.success
    candidateResponse.body.data.name == candidateName
    candidateResponse.body.data.lastName == candidateLastName
    candidateResponse.body.data.email == candidateEmail
  }

  def "test delete candidate"() {
    given:
    candidateService.deleteCandidate(candidateId)

    when:
    def candidateResponse = candidateAPIDelegate.deleteCandidate(candidateId)

    then:
    candidateResponse != null
    candidateResponse.statusCode.value() == 200
    candidateResponse.body.success
    candidateResponse.body.data == null
  }

  def "test get all candidates"() {
    given:
    candidateService.getAllCandidates() >> [createCandidateDTO()]

    when:
    def candidateResponse = candidateAPIDelegate.getAllCandidates()

    then:
    candidateResponse != null
    candidateResponse.statusCode.value() == 200
    candidateResponse.body.success
    candidateResponse.body.data.size() == 1
    candidateResponse.body.data[0].name == candidateName
    candidateResponse.body.data[0].lastName == candidateLastName
    candidateResponse.body.data[0].email == candidateEmail
  }

  private CandidateDTO createCandidateDTO() {
    return new CandidateDTO(id: candidateId, name: candidateName, lastName: candidateLastName,
        email: candidateEmail)
  }
}
