package org.springframework.samples.petclinic.customer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.common.data.BindingErrorsResponse;
import org.springframework.samples.petclinic.common.web.Endpoints;
import org.springframework.samples.petclinic.customer.model.Pet;
import org.springframework.samples.petclinic.customer.service.PetService;
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
@RequestMapping(Endpoints.PETS)
public class PetController {
  private final PetService petService;

  @GetMapping(value = "/{petId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Pet> getPet(@PathVariable("petId") Integer petId) {
    return new ResponseEntity<>(petService.getPetById(petId), HttpStatus.OK);
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Collection<Pet>> getPets() {
    return new ResponseEntity<>(petService.getAllPets(), HttpStatus.OK);
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Pet> addPet(@RequestBody @Valid Pet pet, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (pet == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    petService.savePet(pet);
    headers.setLocation(ucBuilder.path("/api/pets/{id}").buildAndExpand(pet.getId()).toUri());
    return new ResponseEntity<>(pet, headers, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{petId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Pet> updatePet(@PathVariable("petId") Integer petId, @RequestBody @Valid Pet pet, BindingResult bindingResult) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (pet == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(petService.updatePet(pet), HttpStatus.OK);
  }

  @Transactional
  @DeleteMapping(value = "/{petId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Void> deletePet(@PathVariable("petId") Integer petId) {
    petService.deletePet(petId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}