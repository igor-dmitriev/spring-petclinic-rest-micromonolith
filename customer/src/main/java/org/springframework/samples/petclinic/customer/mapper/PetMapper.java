package org.springframework.samples.petclinic.customer.mapper;

import org.springframework.samples.petclinic.customer.api.message.PetResponse;
import org.springframework.samples.petclinic.customer.model.Pet;

public class PetMapper {
  private PetMapper() {

  }

  public static PetResponse toPetResponse(Pet pet) {
    if (pet == null) {
      return null;
    }
    return null;
  }
}
