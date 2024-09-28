package com.seek.testsgonzales.service

import com.seek.testsgonzales.entities.Candidate
import com.seek.testsgonzales.mapper.CandidateMapper
import com.seek.testsgonzales.model.CandidateDTO
import com.seek.testsgonzales.repository.CandidateRepository
import org.mapstruct.factory.Mappers
import spock.lang.Specification
import spock.lang.Subject

class CandidateServiceImplSpec extends Specification {
  @Subject
  CandidateServiceImpl candidateService
  CandidateMapper candidateMapper
  CandidateRepository candidateRepository
  def candidateId = 1

  def setup() {
    candidateRepository = Mock()
    candidateMapper = Mappers.getMapper(CandidateMapper.class)
    candidateService = new CandidateServiceImpl(candidateRepository, candidateMapper)
  }

  def "test get candidate by id"() {
    given:
    candidateRepository.findById(candidateId) >>
        Optional.of(createCandidate())

    when:
    def candidate = candidateService.getCandidateById(candidateId)

    then:
    candidate != null
    candidate.id == candidateId
    candidate.name == "Samuel"
    candidate.lastName == "Gonzales"
    candidate.email == "sagonzales89@gmail.com"
  }

  def "test get candidate by id but not found"() {
    given:
    candidateRepository.findById(candidateId) >>
        Optional.empty()

    when:
    candidateService.getCandidateById(candidateId)

    then:
    def exception = thrown(IllegalArgumentException.class)
    exception.message == "Candidate not found"
  }

  def "test save candidate"() {
    given:
    candidateRepository.save(_ as Candidate) >> createCandidate()
    candidateRepository.findCandidateByEmail(_ as String) >> Optional.empty()

    when:
    def savedCandidate = candidateService.createCandidate(createCandidateDTO())

    then:
    savedCandidate != null
    savedCandidate.id == candidateId
    savedCandidate.name == "Samuel"
    savedCandidate.lastName == "Gonzales"
    savedCandidate.email == "sagonzales89@gmail.com"
  }

  def "test candidate already exists"() {
    given:
    candidateRepository.findCandidateByEmail(_ as String) >> Optional.of(createCandidate())

    when:
    candidateService.createCandidate(createCandidateDTO())

    then:
    def exception = thrown(IllegalArgumentException.class)
    exception.message == "Email already exists"
  }

  def "test update candidate"() {
    given:
    candidateRepository.findById(candidateId) >> Optional.of(createCandidate())
    candidateRepository.save(_ as Candidate) >> createCandidate()

    when:
    def updatedCandidate = candidateService.updateCandidate(candidateId, createCandidateDTO())

    then:
    updatedCandidate != null
    updatedCandidate.id == candidateId
    updatedCandidate.name == "Samuel"
    updatedCandidate.lastName == "Gonzales"
    updatedCandidate.email == "sagonzales89@gmail.com"
  }

  def "test update candidate but not found"() {
    given:
    candidateRepository.findById(candidateId) >> Optional.empty()

    when:
    candidateService.updateCandidate(candidateId, createCandidateDTO())

    then:
    def exception = thrown(IllegalArgumentException.class)
    exception.message == "Candidate not found"
  }

  def "test delete candidate"() {
    given:
    candidateRepository.findById(candidateId) >> Optional.of(createCandidate())

    when:
    candidateService.deleteCandidate(candidateId)

    then:
    1 * candidateRepository.delete(_)
    notThrown(IllegalArgumentException)
  }

  def "test delete candidate but not found"() {
    given:
    candidateRepository.findById(candidateId) >> Optional.empty()

    when:
    candidateService.deleteCandidate(candidateId)

    then:
    def exception = thrown(IllegalArgumentException.class)
    exception.message == "Candidate not found"
  }

  def "test get all candidates"() {
    given:
    candidateRepository.findAll() >> [createCandidate()]

    when:
    def candidates = candidateService.getAllCandidates()

    then:
    candidates != null
    candidates.size() == 1
    candidates[0].id == candidateId
    candidates[0].name == "Samuel"
    candidates[0].lastName == "Gonzales"
    candidates[0].email == "sagonzales89@gmail.com"
  }

  private Candidate createCandidate() {
    return new Candidate(id: candidateId, name: "Samuel", lastName: "Gonzales",
        email: "sagonzales89@gmail.com")
  }

  private CandidateDTO createCandidateDTO() {
    return new CandidateDTO(id: candidateId, name: "Samuel", lastName: "Gonzales",
        email: "sagonzales89@gmail.com")
  }
}
