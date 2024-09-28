package com.seek.testsgonzales.delegate;

import com.seek.testsgonzales.api.CandidateAPIDelegate;
import com.seek.testsgonzales.model.CandidateDTO;
import com.seek.testsgonzales.model.GenericResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CandidateAPIDelegateImpl implements CandidateAPIDelegate {

  @Override
  public ResponseEntity<GenericResponse<CandidateDTO>> createCandidate(CandidateDTO candidateDTO) {
    return null;
  }

  @Override
  public ResponseEntity<GenericResponse<Void>> deleteCandidate(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<GenericResponse<CandidateDTO>> getCandidateById(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<GenericResponse<CandidateDTO>> updateCandidate(Integer id,
      CandidateDTO candidateDTO) {
    return null;
  }

  @Override
  public ResponseEntity<List<GenericResponse<CandidateDTO>>> getAllCandidates() {
    return null;
  }
}
