package org.springframework.samples.petclinic.vets.service;

import org.springframework.samples.petclinic.common.error.ResourceNotFoundException;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.repository.VetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultVetService implements VetService {
  private static final String VET_NOT_FOUND_PATTERN = "Vet with id %d not found";

  private final VetRepository vetRepository;

  @Override
  public Vet getVetById(Integer id) {
    return vetRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(VET_NOT_FOUND_PATTERN, id)));
  }

  @Override
  public Collection<Vet> findAllVets() {
    return vetRepository.findAll();
  }

  @Override
  public void saveVet(Vet vet) {
    vetRepository.save(vet);
  }

  @Override
  public Vet updateVet(Vet vet) {
    return vetRepository.save(vet);
  }

  @Override
  public void deleteVet(Integer id) {
    vetRepository.delete(
        vetRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(VET_NOT_FOUND_PATTERN, id)))
    );
  }
}