package org.springframework.samples.petclinic.customer.service;

import org.springframework.samples.petclinic.customer.entity.AuditEntity;
import org.springframework.samples.petclinic.customer.repository.AuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultAuditService implements AuditService {
  private final AuditRepository auditRepository;

  @Override
  @Transactional
  public void save(AuditEntity auditEntity) {
    auditRepository.save(auditEntity);
  }
}