package com.seek.testsgonzales.api;

import com.seek.testsgonzales.model.CandidateDTO;
import com.seek.testsgonzales.model.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "candidate", description = "Candidates API")
public interface CandidateAPI {

  /**
   * Get the delegate
   *
   * @return CandidateAPIDelegate
   */
  CandidateAPIDelegate getDelegate();

  /**
   * POST /candidate : Create a new candidate
   *
   * @param candidateDTO Candidate Information (required)
   * @return Created candidate (status code 201)
   */
  @Operation(summary = "Create a new candidate", description = "Create a new candidate", tags = {
      "candidate"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PostMapping(value = "/candidate", consumes = {"application/json"}, produces = {
      "application/json"})
  default ResponseEntity<GenericResponse<CandidateDTO>> createCandidate(
      @Parameter(description = "Candidate Information", required = true)
      @RequestBody
      CandidateDTO candidateDTO) {
    return getDelegate().createCandidate(candidateDTO);
  }

  /**
   * GET /candidate/{id} : Get candidate by ID
   *
   * @param id ID of candidate to return (required)
   * @return Candidate Information (status code 200)
   */
  @Operation(summary = "Get candidate by ID", description = "Get candidate by ID", tags = {
      "candidate"})
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping(value = "/candidate/{id}", produces = {"application/json"})
  default ResponseEntity<GenericResponse<CandidateDTO>> getCandidateById(
      @Parameter(description = "ID of candidate to return", required = true)
      @PathVariable("id")
      Integer id) {
    return getDelegate().getCandidateById(id);
  }

  /**
   * GET /candidate : Get all candidates
   *
   * @return List of Candidate Information (status code 200)
   */
  @Operation(summary = "Get all candidates", description = "Get all candidates", tags = {
      "candidate"})
  @SecurityRequirement(name = "Bearer Authentication")
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping(value = "/candidate", produces = {"application/json"})
  default ResponseEntity<GenericResponse<List<CandidateDTO>>> getAllCandidates() {
    return getDelegate().getAllCandidates();
  }

  /**
   * PUT /candidate/{id} : Update candidate
   *
   * @param id           ID of candidate to update (required)
   * @param candidateDTO Candidate Information (required)
   * @return Updated candidate (status code 200)
   */
  @Operation(summary = "Update candidate", description = "Update candidate", tags = {"candidate"})
  @PutMapping(value = "/candidate/{id}", consumes = {"application/json"}, produces = {
      "application/json"})
  default ResponseEntity<GenericResponse<CandidateDTO>> updateCandidate(
      @Parameter(description = "ID of candidate to return", required = true)
      @PathVariable("id")
      Integer id,
      @Parameter(description = "Candidate Information", required = true)
      @RequestBody
      CandidateDTO candidateDTO) {
    return getDelegate().updateCandidate(id, candidateDTO);
  }

  /**
   * DELETE /candidate/{id} : Delete candidate
   *
   * @param id ID of candidate to delete (required)
   * @return Candidate deleted (status code 200)
   */
  @Operation(summary = "Delete candidate", description = "Delete candidate", tags = {"candidate"})
  @DeleteMapping(value = "/candidate/{id}")
  default ResponseEntity<GenericResponse<Void>> deleteCandidate(
      @Parameter(description = "ID of candidate to return", required = true)
      @PathVariable("id")
      Integer id) {
    return getDelegate().deleteCandidate(id);
  }
}
