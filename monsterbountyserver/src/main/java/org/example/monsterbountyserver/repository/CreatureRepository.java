package org.example.monsterbountyserver.repository;

import org.example.monsterbountyserver.entity.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * CreatureRepository provides database access for Creature entities.
 * It uses Spring Data JPA to perform CRUD operations.
 */
public interface CreatureRepository extends JpaRepository<Creature, Long> {

}