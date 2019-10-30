package org.springframework.samples.petclinic.customer.listener;

import org.springframework.context.event.EventListener;
import org.springframework.samples.petclinic.common.event.VisitCreatedEvent;
import org.springframework.samples.petclinic.customer.entity.AuditEntity;
import org.springframework.samples.petclinic.customer.service.AuditService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditEventListener {

  private final AuditService auditService;

  // @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  @EventListener
  public void onVisitCreatedEvent(VisitCreatedEvent event) {
    AuditEntity auditEntity = new AuditEntity();
    String auditDescription = String.format(
        "Visit has been created: visit id: %d, pet id: %d, description: %s",
        event.getVisitId(),
        event.getPetId(),
        event.getDescription()
    );
    auditEntity.setDescription(auditDescription);
    auditService.save(auditEntity);
  }
}