package org.springframework.samples.petclinic.vets.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.common.data.BindingErrorsResponse;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.service.VetService;
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

import javax.transaction.Transactional;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vets")
public class VetController {
  private final VetService vetService;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Collection<Vet>> getAllVets() {
    return new ResponseEntity<>(vetService.findAllVets(), HttpStatus.OK);
  }

  @GetMapping(value = "/{vetId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Vet> getVet(@PathVariable("vetId") Integer vetId) {
    return new ResponseEntity<>(vetService.getVetById(vetId), HttpStatus.OK);
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Vet> addVet(@RequestBody @Valid Vet vet, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (vet == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    vetService.saveVet(vet);
    headers.setLocation(ucBuilder.path("/api/vets/{id}").buildAndExpand(vet.getId()).toUri());
    return new ResponseEntity<>(vet, headers, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{vetId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Vet> updateVet(@PathVariable("vetId") int vetId, @RequestBody @Valid Vet vet, BindingResult bindingResult) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (vet == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(vetService.updateVet(vet), HttpStatus.NO_CONTENT);
  }

  @DeleteMapping(value = "/{vetId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Transactional
  public ResponseEntity<Void> deleteVet(@PathVariable("vetId") int vetId) {
    vetService.deleteVet(vetId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}