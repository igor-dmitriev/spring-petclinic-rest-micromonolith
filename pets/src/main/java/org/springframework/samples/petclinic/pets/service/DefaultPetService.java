package org.springframework.samples.petclinic.pets.service;

import org.springframework.samples.petclinic.common.error.ResourceNotFoundException;
import org.springframework.samples.petclinic.pets.model.Pet;
import org.springframework.samples.petclinic.pets.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

/**
 * @author Igor Dmitriev
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultPetService implements PetService {
  private static final String PET_NOT_FOUND_PATTERN = "Pet with id %d not found";

  private final PetRepository petRepository;

  @Override
  public Pet getPetById(Integer id) {
    return petRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(PET_NOT_FOUND_PATTERN, id)));
  }

  @Override
  public Collection<Pet> getAllPets() {
    return petRepository.findAll();
  }

  @Override
  @Transactional
  public void savePet(Pet pet) {
    petRepository.save(pet);
  }

  @Override
  @Transactional
  public void deletePet(Integer id) {
    petRepository.delete(
        petRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(PET_NOT_FOUND_PATTERN, id)))
    );
  }

  @Override
  @Transactional
  public Pet updatePet(Pet pet) {
    return petRepository.save(pet);
  }
}
