package com.seek.testsgonzales.delegate;

import com.seek.testsgonzales.api.CandidateAPIDelegate;
import com.seek.testsgonzales.model.CandidateDTO;
import com.seek.testsgonzales.model.GenericResponse;
import com.seek.testsgonzales.service.CandidateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CandidateAPIDelegateImpl implements CandidateAPIDelegate {

  private final CandidateService candidateService;

  @Autowired
  public CandidateAPIDelegateImpl(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  @Override
  public ResponseEntity<GenericResponse<CandidateDTO>> createCandidate(CandidateDTO candidateDTO) {
    var genericResponse = GenericResponse.<CandidateDTO>builder()
        .success(true).data(candidateService.createCandidate(candidateDTO)).build();
    return new ResponseEntity<>(genericResponse, HttpStatusCode.valueOf(201));
  }

  @Override
  public ResponseEntity<GenericResponse<Void>> deleteCandidate(Integer id) {
    candidateService.deleteCandidate(id);
    return ResponseEntity.ok(GenericResponse.<Void>builder().success(true).build());
  }

  @Override
  public ResponseEntity<GenericResponse<CandidateDTO>> getCandidateById(Integer id) {
    var genericResponse = GenericResponse.<CandidateDTO>builder()
        .success(true).data(candidateService.getCandidateById(id)).build();
    return ResponseEntity.ok(genericResponse);
  }

  @Override
  public ResponseEntity<GenericResponse<CandidateDTO>> updateCandidate(Integer id,
      CandidateDTO candidateDTO) {
    var genericResponse = GenericResponse.<CandidateDTO>builder()
        .success(true).data(candidateService.updateCandidate(id, candidateDTO)).build();
    return ResponseEntity.ok(genericResponse);
  }

  @Override
  public ResponseEntity<GenericResponse<List<CandidateDTO>>> getAllCandidates() {
    var genericResponse = GenericResponse.<List<CandidateDTO>>builder()
        .success(true).data(candidateService.getAllCandidates()).build();
    return ResponseEntity.ok(genericResponse);
  }
}
