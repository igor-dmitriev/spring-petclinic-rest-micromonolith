package org.springframework.samples.petclinic.visits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.visits.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
}
