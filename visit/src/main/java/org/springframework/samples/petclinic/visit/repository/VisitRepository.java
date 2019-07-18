package org.springframework.samples.petclinic.visit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.visit.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
  long countByPetId(Integer petId);
}