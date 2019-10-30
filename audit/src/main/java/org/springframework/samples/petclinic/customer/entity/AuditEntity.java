package org.springframework.samples.petclinic.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audit")
public class AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq")
  @SequenceGenerator(name = "audit_seq", sequenceName = "audit_seq", allocationSize = 1)
  private Integer id;

  @Column(name = "description")
  private String description;
}