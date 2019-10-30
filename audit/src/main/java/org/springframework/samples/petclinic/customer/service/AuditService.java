package org.springframework.samples.petclinic.customer.service;

import org.springframework.samples.petclinic.customer.entity.AuditEntity;

public interface AuditService {
  void save(AuditEntity auditEntity);
}