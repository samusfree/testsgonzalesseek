package com.seek.testsgonzales.model;

import com.seek.testsgonzales.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
@Schema(name = "CandidateDTO", description = "CandidateDTO Information")
public class CandidateDTO {

  @Schema(name = "id", description = "CandidateDTO ID")
  private Integer id;
  @Schema(name = "name", description = "CandidateDTO Name")
  private String name;
  @Schema(name = "lastName", description = "CandidateDTO Last Name")
  private String lastName;
  @Schema(name = "email", description = "CandidateDTO Email")
  private String email;
  @Schema(name = "phone", description = "CandidateDTO Phone")
  private SexEnum gender;
  @Schema(name = "salaryExpected", description = "CandidateDTO Salary Expected")
  private BigDecimal salaryExpected;
  @Schema(name = "lastApply", description = "CandidateDTO Last Apply")
  private Date lastApply;
}
