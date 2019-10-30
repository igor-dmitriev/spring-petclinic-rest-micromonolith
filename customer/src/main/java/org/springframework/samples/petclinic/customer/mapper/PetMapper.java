package org.springframework.samples.petclinic.customer.mapper;

import org.springframework.samples.petclinic.customer.api.message.PetResponse;
import org.springframework.samples.petclinic.customer.model.Pet;

// TODO: Mapstruct should be used for the rescue
public class PetMapper {
  private PetMapper() {

  }

  public static PetResponse toPetResponse(Pet pet) {
    if (pet == null) {
      return null;
    }
    PetResponse petResponse = new PetResponse();
    petResponse.setId(pet.getId());
    petResponse.setName(pet.getName());
    petResponse.setType(pet.getType().getAnimal());
    return petResponse;
  }
}
