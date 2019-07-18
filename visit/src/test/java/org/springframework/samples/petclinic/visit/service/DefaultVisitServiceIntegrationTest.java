package org.springframework.samples.petclinic.visit.service;

import com.google.common.eventbus.EventBus;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.common.data.AnimalType;
import org.springframework.samples.petclinic.customer.api.message.PetResponse;
import org.springframework.samples.petclinic.customer.api.service.ApiPetService;
import org.springframework.samples.petclinic.visit.model.Visit;
import org.springframework.samples.petclinic.visit.repository.VisitRepository;
import org.springframework.samples.petclinic.visit.util.AbstractPostgresRepositoryTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ExtendWith(DBUnitExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DefaultVisitServiceIntegrationTest extends AbstractPostgresRepositoryTest {

  @SuppressWarnings("unused")
  public ConnectionHolder connectionHolder = () -> dataSource.getConnection();

  @Autowired
  VisitRepository visitRepository;

  private ApiPetService apiPetService;
  private VisitService visitService;

  @BeforeEach
  public void init() {
    apiPetService = Mockito.mock(ApiPetService.class);
    visitService = new DefaultVisitService(visitRepository, apiPetService, new EventBus());
  }

  @Test
  @DataSet(
      value = {"datasets/create-visit.xml"},
      executeScriptsBefore = "datasets/cleanup.sql",
      strategy = SeedStrategy.INSERT
  )
  public void shouldSaveVisit() {
    Visit visit = Visit.builder()
        .petId(100)
        .date(visitDate())
        .description("test description")
        .build();
    PetResponse petResponse = PetResponse.builder().type(AnimalType.DOG).build();
    Mockito.when(apiPetService.getPet(100)).thenReturn(petResponse);
    visitService.saveVisit(visit);

    flushAndClear();

    Visit actual = visitRepository.getOne(1);
    assertThat(actual.getId()).isEqualTo(1);
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