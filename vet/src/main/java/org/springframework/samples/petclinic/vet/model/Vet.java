/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing a veterinarian.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 * @author Igor Dmitriev
 */
@Entity
@Getter
@Setter
@Table(name = "vet")
public class Vet {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vet_seq")
  @SequenceGenerator(name = "vet_seq", sequenceName = "vet_seq", allocationSize = 1)
  private Integer id;

  @Column(name = "first_name")
  @NotEmpty
  protected String firstName;

  @Column(name = "last_name")
  @NotEmpty
  protected String lastName;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
      inverseJoinColumns = @JoinColumn(name = "specialty_id"))
  private Set<Specialty> specialties = new HashSet<>();

  public void addSpecialty(Specialty specialty) {
    this.specialties.add(specialty);
  }

  public void clearSpecialties() {
    this.specialties.clear();
  }
}