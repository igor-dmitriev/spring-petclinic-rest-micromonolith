package org.springframework.samples.petclinic.customer.service;

import org.springframework.samples.petclinic.customer.model.PetType;

import java.util.Collection;

public interface PetTypeService {
  PetType getPetTypeById(Integer id);

  Collection<PetType> findAllPetTypes();

  void savePetType(PetType petType);

  void deletePetType(Integer id);

  PetType updatePetType(PetType petType);
}
