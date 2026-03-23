package org.example.monsterbountyserver.controller;

import org.example.monsterbountyserver.entity.Creature;
import org.example.monsterbountyserver.repository.CreatureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreatureController {

    private final CreatureRepository creatureRepository;

    public CreatureController(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    @GetMapping("/creatures")
    public List<Creature> getAllCreatures() {
        return creatureRepository.findAll();
    }
}