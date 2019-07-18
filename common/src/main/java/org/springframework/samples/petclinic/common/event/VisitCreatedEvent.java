package org.springframework.samples.petclinic.common.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class VisitCreatedEvent {
  private final Integer visitId;
  private final String description;
  private final Integer petId;
}
