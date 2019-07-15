package org.springframework.samples.petclinic.visits.service;

import org.springframework.samples.petclinic.visits.model.Visit;

import java.util.Collection;

public interface VisitService {
  Visit getVisitById(int visitId);

  Collection<Visit> findAllVisits();

  void saveVisit(Visit visit);

  void deleteVisit(Integer id);

  Visit updateVisit(Visit visit);
}
