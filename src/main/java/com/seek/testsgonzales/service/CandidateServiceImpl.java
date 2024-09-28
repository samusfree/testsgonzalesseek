package com.seek.testsgonzales.service;

import com.seek.testsgonzales.entities.Candidate;
import com.seek.testsgonzales.mapper.CandidateMapper;
import com.seek.testsgonzales.model.CandidateDTO;
import com.seek.testsgonzales.repository.CandidateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

  private final CandidateRepository candidateRepository;
  private final CandidateMapper candidateMapper;

  @Autowired
  public CandidateServiceImpl(CandidateRepository candidateRepository,
      CandidateMapper candidateMapper) {
    this.candidateRepository = candidateRepository;
    this.candidateMapper = candidateMapper;
  }

  @Override
  public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
    candidateRepository.findCandidateByEmail(candidateDTO.getEmail())
        .ifPresent(candidate -> {
          throw new IllegalArgumentException("Email already exists");
        });

    return saveOrUpdateCandidate(candidateDTO);
  }

  private CandidateDTO saveOrUpdateCandidate(CandidateDTO candidateDTO) {
    Candidate candidate = candidateMapper.toEntity(candidateDTO);
    candidate = candidateRepository.save(candidate);
    return candidateMapper.toDTO(candidate);
  }

  @Override
  public CandidateDTO getCandidateById(Integer id) {
    return candidateRepository.findById(id)
        .map(candidateMapper::toDTO)
        .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
  }

  @Override
  public CandidateDTO updateCandidate(Integer id, CandidateDTO candidateDTO) {
    candidateDTO.setId(id);
    candidateRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
    return saveOrUpdateCandidate(candidateDTO);
  }

  @Override
  public List<CandidateDTO> getAllCandidates() {
    return candidateRepository.findAll().stream().map(candidateMapper::toDTO)
        .toList();
  }

  @Override
  public void deleteCandidate(Integer id) {
    candidateRepository.findById(id)
        .ifPresentOrElse(candidateRepository::delete,
            () -> {
              throw new IllegalArgumentException("Candidate not found");
            });
  }
}
