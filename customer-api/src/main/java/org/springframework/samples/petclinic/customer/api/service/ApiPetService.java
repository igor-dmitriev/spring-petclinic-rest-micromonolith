package org.springframework.samples.petclinic.customer.api.service;

import org.springframework.samples.petclinic.customer.api.message.PetResponse;

public interface ApiPetService {
  PetResponse getPet(Integer id);
}
