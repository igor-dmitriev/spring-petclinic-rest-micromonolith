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

import org.springframework.samples.petclinic.common.data.AnimalType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Juergen Hoeller
 * @author Igor Dmitriev
 */
@Entity
@Getter
@Setter
@Table(name = "pet_type")
public class PetType {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_type_seq")
  @SequenceGenerator(name = "pet_type_seq", sequenceName = "pet_type_seq", allocationSize = 1)
  private Integer id;

  @Column(name = "animal", nullable = false)
  @Enumerated(EnumType.STRING)
  private AnimalType animal;
}
