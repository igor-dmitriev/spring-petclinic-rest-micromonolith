package org.springframework.samples.petclinic.visit.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.common.web.Endpoints;
import org.springframework.samples.petclinic.visit.model.Visit;
import org.springframework.samples.petclinic.visit.service.VisitService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(VisitController.class)
public class VisitControllerIntegrationTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  VisitService visitService;

  @Test
  public void shouldReturnVisitById() throws Exception {
    Visit expectedVisit = Visit.builder()
        .id(1)
        .description("test description")
        .petId(1)
        .build();
    Mockito.when(visitService.getVisitById(1)).thenReturn(expectedVisit);

    mockMvc.perform(get(Endpoints.VISIT + "/" + 1)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.description").value("test description"))
        .andExpect(jsonPath("$.petId").value(1));
  }
}
