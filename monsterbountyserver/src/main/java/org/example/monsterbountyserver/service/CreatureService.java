package org.example.monsterbountyserver.service;

import org.example.monsterbountyserver.dto.CreatureRequest;
import org.example.monsterbountyserver.dto.CreatureResponse;
import org.example.monsterbountyserver.entity.Creature;
import org.example.monsterbountyserver.entity.Habitat;
import org.example.monsterbountyserver.repository.CreatureRepository;
import org.example.monsterbountyserver.repository.HabitatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreatureService {

    private final CreatureRepository creatureRepository;
    private final HabitatRepository habitatRepository;

    public CreatureService(CreatureRepository creatureRepository, HabitatRepository habitatRepository) {
        this.creatureRepository = creatureRepository;
        this.habitatRepository = habitatRepository;
    }

    public List<Creature> getAllCreatures() {
        return creatureRepository.findAll();
    }

    public Optional<Creature> getCreatureById(Long id) {
        return creatureRepository.findById(id);
    }

    public CreatureResponse createCreature(CreatureRequest request) {
        Habitat habitat = habitatRepository.findById(request.habitatId)
                .orElseThrow(() -> new RuntimeException("Habitat not found"));

        Creature creature = new Creature();
        creature.setName(request.name);
        creature.setSpecies(request.species);
        creature.setDangerLevel(request.dangerLevel);
        creature.setCondition(request.condition);
        creature.setNotes(request.notes);
        creature.setHabitat(habitat);

        Creature savedCreature = creatureRepository.save(creature);

        CreatureResponse response = new CreatureResponse();
        response.id = savedCreature.getId();
        response.name = savedCreature.getName();
        response.species = savedCreature.getSpecies();
        response.dangerLevel = savedCreature.getDangerLevel();
        response.condition = savedCreature.getCondition();
        response.notes = savedCreature.getNotes();
        response.habitatId = savedCreature.getHabitat().getId();
        response.createdAt = savedCreature.getCreatedAt();

        return response;
    }
}