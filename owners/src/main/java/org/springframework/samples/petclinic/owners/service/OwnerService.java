package org.springframework.samples.petclinic.owners.service;

import org.springframework.samples.petclinic.owners.model.Owner;

import java.util.Collection;

public interface OwnerService {
  Owner getOwnerById(Integer id);

  Owner updateOwner(Owner owner);

  Collection<Owner> findAllOwners();

  void saveOwner(Owner owner);

  void deleteOwner(Integer id);

  Collection<Owner> findOwnerByLastName(String lastName);
}
