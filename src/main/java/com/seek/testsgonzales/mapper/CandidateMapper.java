package com.seek.testsgonzales.mapper;

import com.seek.testsgonzales.entities.Candidate;
import com.seek.testsgonzales.model.CandidateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

  CandidateDTO toDTO(Candidate candidate);

  Candidate toEntity(CandidateDTO candidateDTO);
}
