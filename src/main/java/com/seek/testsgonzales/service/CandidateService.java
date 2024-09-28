package com.seek.testsgonzales.service;

import com.seek.testsgonzales.model.CandidateDTO;
import java.util.List;

public interface CandidateService {

  /**
   * Create a new candidate.
   *
   * @param candidateDTO the candidate to create
   * @return the created candidate
   */
  CandidateDTO createCandidate(CandidateDTO candidateDTO);

  /**
   * Get a candidate by ID.
   *
   * @param id the ID of the candidate to get
   * @return the candidate
   */
  CandidateDTO getCandidateById(Integer id);

  /**
   * Update a candidate.
   *
   * @param id           the ID of the candidate to update
   * @param candidateDTO the candidate to update
   * @return the updated candidate
   */
  CandidateDTO updateCandidate(Integer id, CandidateDTO candidateDTO);

  /**
   * Get all candidates.
   *
   * @return the list of candidates
   */
  List<CandidateDTO> getAllCandidates();

  /**
   * Delete a candidate by ID.
   *
   * @param id the ID of the candidate to delete
   */
  void deleteCandidate(Integer id);
}
