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

    public List<CreatureResponse> getAllCreatures() {
        return creatureRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CreatureResponse> getCreatureById(Long id) {
        return creatureRepository.findById(id)
                .map(this::toResponse);
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

        return toResponse(savedCreature);
    }

    private CreatureResponse toResponse(Creature creature) {
        CreatureResponse response = new CreatureResponse();
        response.id = creature.getId();
        response.name = creature.getName();
        response.species = creature.getSpecies();
        response.dangerLevel = creature.getDangerLevel();
        response.condition = creature.getCondition();
        response.notes = creature.getNotes();
        response.habitatId = creature.getHabitat().getId();
        response.createdAt = creature.getCreatedAt();
        return response;
    }
}