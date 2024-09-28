package com.seek.testsgonzales.model;

import com.seek.testsgonzales.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
@Schema(name = "CandidateDTO", description = "CandidateDTO Information")
public class CandidateDTO {

  @Schema(name = "id", description = "Candidate ID")
  private Integer id;
  @Schema(name = "name", description = "Candidate Name")
  private String name;
  @Schema(name = "lastName", description = "Candidate Last Name")
  private String lastName;
  @Schema(name = "email", description = "Candidate Email")
  private String email;
  @Schema(name = "gender", description = "Candidate Gender")
  private SexEnum gender;
  @Schema(name = "salaryExpected", description = "Candidate Salary Expected")
  private BigDecimal salaryExpected;
  @Schema(name = "lastApply", description = "Candidate Last Apply")
  private Date lastApply;
}
