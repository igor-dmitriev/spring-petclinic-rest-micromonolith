package org.springframework.samples.petclinic.vet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "specialty")
public class Specialty {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialty_seq")
  @SequenceGenerator(name = "specialty_seq", sequenceName = "specialty_seq", allocationSize = 1)
  private Integer id;

  @Column(name = "name")
  @NotEmpty
  private String name;
}
