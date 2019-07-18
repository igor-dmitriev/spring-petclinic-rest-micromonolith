package org.springframework.samples.petclinic.customer.listener;

import com.google.common.eventbus.Subscribe;

import org.springframework.samples.petclinic.common.event.VisitCreatedEvent;

public class AuditEventListener {
  @Subscribe
  public void onAuditDataEvent(VisitCreatedEvent event) {
    System.out.println(event);
  }
}
