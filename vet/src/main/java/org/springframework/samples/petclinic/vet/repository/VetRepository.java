package org.springframework.samples.petclinic.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.vet.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Integer> {
}
