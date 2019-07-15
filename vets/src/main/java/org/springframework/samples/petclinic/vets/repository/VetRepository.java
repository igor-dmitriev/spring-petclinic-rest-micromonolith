package org.springframework.samples.petclinic.vets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.vets.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Integer> {
}
