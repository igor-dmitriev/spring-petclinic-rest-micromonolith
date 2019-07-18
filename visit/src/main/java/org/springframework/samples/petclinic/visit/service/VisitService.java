package org.springframework.samples.petclinic.visit.service;

import org.springframework.samples.petclinic.visit.model.Visit;

import java.util.Collection;

public interface VisitService {
  Visit getVisitById(int visitId);

  Collection<Visit> getAllVisits();

  void saveVisit(Visit visit);

  void deleteVisit(Integer id);

  Visit updateVisit(Visit visit);
}
