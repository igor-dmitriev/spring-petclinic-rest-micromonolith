package org.springframework.samples.petclinic.customer.api.message;

import org.springframework.samples.petclinic.common.data.AnimalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {
  private Integer id;
  private String name;
  private AnimalType type;
}