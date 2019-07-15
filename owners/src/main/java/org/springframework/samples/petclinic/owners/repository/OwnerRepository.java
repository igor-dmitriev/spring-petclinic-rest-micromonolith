package org.springframework.samples.petclinic.owners.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owners.model.Owner;

import java.util.Collection;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
  @Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
  Collection<Owner> findByLastNameFetchPets(@Param("lastName") String lastName);

  @Query("SELECT distinct owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
  Optional<Owner> findByIdFetchPets(@Param("id") int id);
}