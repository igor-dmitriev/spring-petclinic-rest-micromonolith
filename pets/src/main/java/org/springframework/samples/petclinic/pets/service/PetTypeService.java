package org.springframework.samples.petclinic.pets.service;

import org.springframework.samples.petclinic.pets.model.PetType;

import java.util.Collection;

public interface PetTypeService {
  PetType getPetTypeById(Integer id);

  Collection<PetType> findAllPetTypes();

  void savePetType(PetType petType);

  void deletePetType(Integer id);

  PetType updatePetType(PetType petType);
}
