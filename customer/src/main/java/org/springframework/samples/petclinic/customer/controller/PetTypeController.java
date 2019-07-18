package org.springframework.samples.petclinic.customer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.common.data.BindingErrorsResponse;
import org.springframework.samples.petclinic.customer.model.PetType;
import org.springframework.samples.petclinic.customer.service.PetTypeService;
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
@RequestMapping("api/pettypes")
public class PetTypeController {
  private final PetTypeService petTypeService;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Collection<PetType>> getAllPetTypes() {
    return new ResponseEntity<>(petTypeService.findAllPetTypes(), HttpStatus.OK);
  }

  @GetMapping(value = "/{petTypeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PetType> getPetType(@PathVariable("petTypeId") Integer petTypeId) {
    return new ResponseEntity<>(petTypeService.getPetTypeById(petTypeId), HttpStatus.OK);
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PetType> addPetType(@RequestBody @Valid PetType petType, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (petType == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    petTypeService.savePetType(petType);
    headers.setLocation(ucBuilder.path("/api/pettypes/{id}").buildAndExpand(petType.getId()).toUri());
    return new ResponseEntity<>(petType, headers, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{petTypeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PetType> updatePetType(@PathVariable("petTypeId") Integer petTypeId, @RequestBody @Valid PetType petType, BindingResult bindingResult) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (petType == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(petTypeService.updatePetType(petType), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{petTypeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Void> deletePetType(@PathVariable("petTypeId") Integer id) {
    petTypeService.deletePetType(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
