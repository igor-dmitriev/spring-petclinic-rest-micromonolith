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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Igor Dmitriev
 */
@Entity
@Getter
@Setter
@Table(name = "owner")
public class Owner {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq")
  @SequenceGenerator(name = "owner_seq", sequenceName = "owner_seq", allocationSize = 1)
  private Integer id;

  @Column(name = "address")
  @NotEmpty
  private String address;

  @Column(name = "city")
  @NotEmpty
  private String city;

  @Column(name = "telephone")
  @NotEmpty
  @Digits(fraction = 0, integer = 10)
  private String telephone;

  @Column(name = "first_name")
  @NotEmpty
  protected String firstName;

  @Column(name = "last_name")
  @NotEmpty
  protected String lastName;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private Set<Pet> pets = new HashSet<>();
}
