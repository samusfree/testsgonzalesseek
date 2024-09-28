package com.seek.testsgonzales.mapper

import com.seek.testsgonzales.entities.Candidate
import com.seek.testsgonzales.model.CandidateDTO
import org.mapstruct.factory.Mappers
import spock.lang.Specification
import spock.lang.Subject

class CandidateMapperSpec extends Specification {
  @Subject
  CandidateMapper candidateMapper = Mappers.getMapper(CandidateMapper)

  def "should map Candidate to CandidateDTO"() {
    given:
    Candidate candidate = new Candidate(id: 1, name: "Samuel", email: "sagonzales89@gmail.com")

    when:
    CandidateDTO candidateDTO = candidateMapper.toDTO(candidate)

    then:
    candidateDTO.id == candidate.id
    candidateDTO.name == candidate.name
    candidateDTO.email == candidate.email
  }

  def "should map Candidate to CandidateDTO"() {
    given:
    Candidate candidate = new Candidate(id: 1, name: "Samuel", email: "sagonzales89@gmail.com")

    when:
    CandidateDTO candidateDTO = candidateMapper.toDTO(candidate)

    then:
    candidateDTO.id == candidate.id
    candidateDTO.name == candidate.name
    candidateDTO.email == candidate.email
  }

  def "should map null Candidate to null CandidateDTO"() {
    given:

    when:
    CandidateDTO candidateDTO = candidateMapper.toDTO(null)

    then:
    candidateDTO == null
  }

  def "should map CandidateDTO to Candidate"() {
    given:
    CandidateDTO candidateDTO = new CandidateDTO(id: 1, name: "Samuel", email: "sagonzales89@gmail.com")

    when:
    Candidate candidate = candidateMapper.toEntity(candidateDTO)

    then:
    candidate.id == candidateDTO.id
    candidate.name == candidateDTO.name
    candidate.email == candidateDTO.email
  }

  def "should map null CandidateDTO to null Candidate"() {
    given:

    when:
    Candidate candidate = candidateMapper.toEntity(null)

    then:
    candidate == null
  }
}
