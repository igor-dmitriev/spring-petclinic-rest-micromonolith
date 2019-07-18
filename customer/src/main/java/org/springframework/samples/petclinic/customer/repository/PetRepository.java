package org.springframework.samples.petclinic.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.customer.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
