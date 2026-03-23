package org.example.monsterbountyserver.repository;

import org.example.monsterbountyserver.entity.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureRepository extends JpaRepository<Creature, Long> {

}