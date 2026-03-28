package org.example.monsterbountyserver.repository;

import org.example.monsterbountyserver.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * HabitatRepository provides database access for Habitat entities.
 * It uses Spring Data JPA to perform CRUD operations.
 */

public interface HabitatRepository extends JpaRepository<Habitat, Long> {

}