package org.springframework.samples.petclinic.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.customer.entity.AuditEntity;

public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}