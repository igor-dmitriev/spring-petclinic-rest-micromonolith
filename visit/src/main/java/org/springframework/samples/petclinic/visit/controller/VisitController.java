package org.springframework.samples.petclinic.visit.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.common.data.BindingErrorsResponse;
import org.springframework.samples.petclinic.common.web.Endpoints;
import org.springframework.samples.petclinic.visit.model.Visit;
import org.springframework.samples.petclinic.visit.service.VisitService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.VISIT)
public class VisitController {

  private final VisitService visitService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Collection<Visit>> getAllVisits() {
    return new ResponseEntity<>(visitService.getAllVisits(), HttpStatus.OK);
  }

  @GetMapping(value = "/{visitId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Visit> getVisit(@PathVariable("visitId") Integer visitId) {
    return new ResponseEntity<>(visitService.getVisitById(visitId), HttpStatus.OK);
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Visit> addVisit(@RequestBody @Valid Visit visit, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (visit == null) || (visit.getPetId() == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    visitService.saveVisit(visit);
    headers.setLocation(ucBuilder.path("/api/visit/{id}").buildAndExpand(visit.getId()).toUri());
    return new ResponseEntity<>(visit, headers, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{visitId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Visit> updateVisit(@PathVariable("visitId") Integer visitId, @RequestBody @Valid Visit visit, BindingResult bindingResult) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (visit == null) || (visit.getPetId() == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(visitService.updateVisit(visit), HttpStatus.NO_CONTENT);
  }

  @DeleteMapping(value = "/{visitId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Void> deleteVisit(@PathVariable("visitId") Integer visitId) {
    visitService.deleteVisit(visitId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
