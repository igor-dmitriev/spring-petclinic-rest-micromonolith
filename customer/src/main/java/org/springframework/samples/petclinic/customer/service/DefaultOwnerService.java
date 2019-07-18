package org.springframework.samples.petclinic.customer.service;

import org.springframework.samples.petclinic.common.error.ResourceNotFoundException;
import org.springframework.samples.petclinic.customer.model.Owner;
import org.springframework.samples.petclinic.customer.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultOwnerService implements OwnerService {
  private static final String OWNER_NOT_FOUND_PATTERN = "Owner with id %d not found";

  private final OwnerRepository ownerRepository;

  @Override
  public Owner getOwnerById(Integer id) {
    return ownerRepository.findByIdFetchPets(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(OWNER_NOT_FOUND_PATTERN, id)));
  }

  @Override
  @Transactional
  public Owner updateOwner(Owner updatedOwner) {
    return ownerRepository.save(updatedOwner);
  }

  @Override
  public Collection<Owner> findAllOwners() {
    return ownerRepository.findAll();
  }

  @Override
  @Transactional
  public void saveOwner(Owner owner) {
    ownerRepository.save(owner);
  }

  @Override
  @Transactional
  public void deleteOwner(Integer id) {
    ownerRepository.delete(
        ownerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(OWNER_NOT_FOUND_PATTERN, id)))
    );
  }

  @Override
  public Collection<Owner> findOwnerByLastName(String lastName) {
    return ownerRepository.findByLastNameFetchPets(lastName);
  }
}
