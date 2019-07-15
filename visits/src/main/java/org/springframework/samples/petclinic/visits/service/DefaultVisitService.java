package org.springframework.samples.petclinic.visits.service;

import org.springframework.samples.petclinic.common.error.ResourceNotFoundException;
import org.springframework.samples.petclinic.visits.model.Visit;
import org.springframework.samples.petclinic.visits.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultVisitService implements VisitService {

  private static final String VISIT_NOT_FOUND_PATTERN = "Visit with id %d not found";

  private final VisitRepository visitRepository;

  @Override
  public Visit getVisitById(int id) {
    return visitRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(VISIT_NOT_FOUND_PATTERN, id)));
  }

  @Override
  public Collection<Visit> findAllVisits() {
    return visitRepository.findAll();
  }

  @Override
  @Transactional
  public void saveVisit(Visit visit) {
    visitRepository.save(visit);
  }

  @Override
  @Transactional
  public void deleteVisit(Integer id) {
    visitRepository.delete(
        visitRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(VISIT_NOT_FOUND_PATTERN, id)))
    );
  }

  @Override
  @Transactional
  public Visit updateVisit(Visit visit) {
    return visitRepository.save(visit);
  }
}
