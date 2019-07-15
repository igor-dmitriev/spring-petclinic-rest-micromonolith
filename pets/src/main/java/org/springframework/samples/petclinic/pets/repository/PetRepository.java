package org.springframework.samples.petclinic.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.pets.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
