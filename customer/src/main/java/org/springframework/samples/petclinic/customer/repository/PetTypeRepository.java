package org.springframework.samples.petclinic.customer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.customer.model.PetType;

public interface PetTypeRepository extends JpaRepository<PetType, Integer> {
}
