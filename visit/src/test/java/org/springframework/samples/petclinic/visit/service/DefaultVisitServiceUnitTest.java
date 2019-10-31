package org.springframework.samples.petclinic.visit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.samples.petclinic.common.data.AnimalType;
import org.springframework.samples.petclinic.common.error.ResourceNotFoundException;
import org.springframework.samples.petclinic.common.error.VisitsAmountIsExceededException;
import org.springframework.samples.petclinic.customer.api.message.PetResponse;
import org.springframework.samples.petclinic.customer.api.service.ApiPetService;
import org.springframework.samples.petclinic.visit.model.Visit;
import org.springframework.samples.petclinic.visit.repository.VisitRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultVisitServiceUnitTest {

  private VisitService visitService;

  @Mock
  VisitRepository visitRepository;

  @Mock
  ApiPetService apiPetService;

  @Mock
  ApplicationEventPublisher eventPublisher;

  @BeforeEach
  void init() {
    visitService = new DefaultVisitService(visitRepository, apiPetService, eventPublisher);
  }

  @Test
  void shouldSaveVisit() {
    // given
    PetResponse petResponse = PetResponse.builder()
        .id(1)
        .name("test pet")
        .type(AnimalType.BIRD)
        .build();
    when(apiPetService.getPet(1)).thenReturn(petResponse);
    Visit visitToSave = Visit.builder().petId(1).build();

    // when
    visitService.saveVisit(visitToSave);

    // then
    verify(visitRepository, times(1)).saveAndFlush(visitToSave);
  }

  @Test
  void shouldThrownVisitsAmountExceededExceptionWhenAnimalTypeIsLizardAndCountOfVisitsIs5() {
    // given
    PetResponse petResponse = PetResponse.builder()
        .id(1)
        .name("test pet")
        .type(AnimalType.LIZARD)
        .build();
    when(apiPetService.getPet(1)).thenReturn(petResponse);
    when(visitRepository.countByPetId(Mockito.anyInt())).thenReturn(5L);

    // expect
    assertThrows(
        VisitsAmountIsExceededException.class,
        () -> visitService.saveVisit(Visit.builder().petId(1).build())
    );
  }

  @Test
  void visitMayBeDeleted() {
    // given
    Visit visitToDelete = Visit.builder().id(1).build();
    when(visitRepository.findById(1)).thenReturn(Optional.of(visitToDelete));

    // when
    visitService.deleteVisit(1);

    // then
    verify(visitRepository, times(1)).delete(visitToDelete);
  }

  @Test
  void visitMayBeUpdated() {
    // given
    Visit visitToUpdate = Visit.builder().id(1).build();

    // when
    visitService.updateVisit(visitToUpdate);

    // then
    verify(visitRepository, times(1)).save(visitToUpdate);
  }

  @Test
  void shouldGetAllVisits() {
    // given
    List<Visit> visits = Arrays.asList(
        Visit.builder().build(),
        Visit.builder().build(),
        Visit.builder().build()
    );

    // when
    when(visitRepository.findAll()).thenReturn(visits);

    // then
    assertThat(visitService.getAllVisits().equals(visits)).isTrue();
  }

  @Test
  void shouldGetVisitById() {
    // given
    Visit visit = Visit.builder().id(1).build();

    // when
    when(visitRepository.findById(1)).thenReturn(Optional.of(visit));

    // then
    assertThat(visitService.getVisitById(1).equals(visit)).isTrue();
  }

  @Test
  void shouldThrownResourceNotFoundIfThereIsNoVisitById() {
    // given
    when(visitRepository.findById(1)).thenReturn(Optional.empty());

    // expect
    assertThrows(
        ResourceNotFoundException.class,
        () -> visitService.getVisitById(1)
    );
  }
}