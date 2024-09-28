package com.seek.testsgonzales.api;

import com.seek.testsgonzales.model.CandidateDTO;
import com.seek.testsgonzales.model.GenericResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CandidateAPIDelegate {

  /**
   * POST /candidate/ : Create a new candidate
   *
   * @param candidateDTO Candidate Information (required)
   * @return Created candidate (status code 201)
   * @see CandidateAPI#createCandidate
   */
  ResponseEntity<GenericResponse<CandidateDTO>> createCandidate(CandidateDTO candidateDTO);

  /**
   * DELETE /candidate/{id} : Delete candidate by ID
   *
   * @param id ID of candidate to delete (required)
   * @return Deleted candidate (status code 200)
   * @see CandidateAPI#deleteCandidate
   */
  ResponseEntity<GenericResponse<Void>> deleteCandidate(Integer id);

  /**
   * GET /candidate/{id} : Get candidate by ID
   *
   * @param id ID of candidate to return (required)
   * @return Candidate Information (status code 200)
   * @see CandidateAPI#getCandidateById
   */
  ResponseEntity<GenericResponse<CandidateDTO>> getCandidateById(Integer id);

  /**
   * PUT /candidate/{id} : Update candidate
   *
   * @param id           ID of candidate to update (required)
   * @param candidateDTO Candidate Information (required)
   * @return Updated candidate (status code 200)
   * @see CandidateAPI#updateCandidate
   */
  ResponseEntity<GenericResponse<CandidateDTO>> updateCandidate(Integer id,
      CandidateDTO candidateDTO);

  /**
   * GET /candidate : Get all candidates
   *
   * @return List of Candidate Information (status code 200)
   * @see CandidateAPI#getAllCandidates
   */
  ResponseEntity<List<GenericResponse<CandidateDTO>>> getAllCandidates();
}
