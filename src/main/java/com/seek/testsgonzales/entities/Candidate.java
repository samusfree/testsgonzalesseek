package com.seek.testsgonzales.entities;

import com.seek.testsgonzales.enums.SexEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "name")
  private String name;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  private String email;
  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private SexEnum gender;
  @Column(name = "salary_expected")
  private BigDecimal salaryExpected;
  @Column(name = "last_apply")
  private Date lastApply;
}
