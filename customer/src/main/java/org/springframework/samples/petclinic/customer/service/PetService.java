package org.springframework.samples.petclinic.customer.service;

import org.springframework.samples.petclinic.customer.model.Pet;

import java.util.Collection;

public interface PetService {
  Pet getPetById(Integer id);

  Collection<Pet> getAllPets();

  void savePet(Pet pet);

  void deletePet(Integer id);

  Pet updatePet(Pet pet);
}
