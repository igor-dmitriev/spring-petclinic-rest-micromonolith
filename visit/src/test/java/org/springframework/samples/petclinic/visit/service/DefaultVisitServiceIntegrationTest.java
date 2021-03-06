package org.springframework.samples.petclinic.visit.service;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.samples.petclinic.common.data.AnimalType;
import org.springframework.samples.petclinic.customer.api.message.PetResponse;
import org.springframework.samples.petclinic.customer.api.service.ApiPetService;
import org.springframework.samples.petclinic.visit.model.Visit;
import org.springframework.samples.petclinic.visit.repository.VisitRepository;
import org.springframework.samples.petclinic.visit.util.AbstractPostgresRepositoryTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Calendar;
import java.util.Date;

@DBRider
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DefaultVisitServiceIntegrationTest extends AbstractPostgresRepositoryTest {

  @SuppressWarnings("unused")
  public ConnectionHolder connectionHolder = () -> dataSource.getConnection();

  @Autowired
  VisitRepository visitRepository;

  @Autowired
  ApplicationEventPublisher eventPublisher;

  private ApiPetService apiPetService;
  private VisitService visitService;

  @BeforeEach
  public void init() {
    apiPetService = Mockito.mock(ApiPetService.class);
    visitService = new DefaultVisitService(visitRepository, apiPetService, eventPublisher);
  }

  @Test
  @DataSet("datasets/create-visit.xml")
  @ExpectedDataSet("datasets/create-visit-expected.xml")
  public void shouldSaveVisit() {
    // given
    Visit visit = Visit.builder()
        .petId(100)
        .date(visitDate())
        .description("test description")
        .build();
    PetResponse petResponse = PetResponse.builder().type(AnimalType.DOG).build();

    // when
    Mockito.when(apiPetService.getPet(100)).thenReturn(petResponse);
    visitService.saveVisit(visit);
  }

  private Date visitDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH, 1);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.HOUR_OF_DAY, 7);
    cal.set(Calendar.MINUTE, 17);
    cal.set(Calendar.SECOND, 10);
    return cal.getTime();
  }
}