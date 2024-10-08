package com.seek.testsgonzales.repository;

import com.seek.testsgonzales.entities.Candidate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

  Optional<Candidate> findCandidateByEmail(String email);

}
