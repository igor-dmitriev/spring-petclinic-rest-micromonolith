package org.springframework.samples.petclinic.vet.service;

import org.springframework.samples.petclinic.vet.model.Vet;

import java.util.Collection;

public interface VetService {
  Vet getVetById(Integer id);

  Collection<Vet> findAllVets();

  void saveVet(Vet vet);

  void deleteVet(Integer id);

  Vet updateVet(Vet vet);
}