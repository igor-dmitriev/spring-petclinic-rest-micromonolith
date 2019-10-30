/*
 * Copyright 2002-2013 the original author or authors.
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
package org.springframework.samples.petclinic.customer.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple business object representing a pet.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Igor Dmitriev
 */
@Entity
@Getter
@Setter
@Table(name = "pet")
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
  @SequenceGenerator(name = "pet_seq", sequenceName = "pet_seq", allocationSize = 1)
  private Integer id;

  @Column(name = "name")
  @NotEmpty
  private String name;

  @Column(name = "birth_date")
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date birthDate;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private PetType type;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Owner owner;
}