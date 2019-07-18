package org.springframework.samples.petclinic.customer.service;

import org.springframework.samples.petclinic.common.error.ResourceNotFoundException;
import org.springframework.samples.petclinic.customer.model.PetType;
import org.springframework.samples.petclinic.customer.repository.PetTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultPetTypeService implements PetTypeService {

  private static final String PET_TYPE_NOT_FOUND_PATTERN = "Pet type with id %d not found";

  private final PetTypeRepository petTypeRepository;

  @Override
  public PetType getPetTypeById(Integer id) {
    return petTypeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(PET_TYPE_NOT_FOUND_PATTERN, id)));
  }

  @Override
  public Collection<PetType> findAllPetTypes() {
    return petTypeRepository.findAll();
  }

  @Override
  public void savePetType(PetType petType) {
    petTypeRepository.save(petType);
  }

  @Override
  public void deletePetType(Integer id) {
    petTypeRepository.delete(
        petTypeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(PET_TYPE_NOT_FOUND_PATTERN, id)))
    );
  }

  @Override
  public PetType updatePetType(PetType petType) {
    return petTypeRepository.save(petType);
  }
}
