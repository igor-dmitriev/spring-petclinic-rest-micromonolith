package org.springframework.samples.petclinic.customer.api.service;

import org.springframework.samples.petclinic.customer.api.message.PetResponse;
import org.springframework.samples.petclinic.customer.mapper.PetMapper;
import org.springframework.samples.petclinic.customer.service.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultApiPetService implements ApiPetService {

  private final PetService petService;

  @Override
  public PetResponse getPet(Integer id) {
    return PetMapper.toPetResponse(petService.getPetById(id));
  }
}
