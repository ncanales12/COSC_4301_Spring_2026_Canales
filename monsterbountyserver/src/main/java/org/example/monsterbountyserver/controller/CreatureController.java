package org.example.monsterbountyserver.controller;

import org.example.monsterbountyserver.entity.Creature;
import org.example.monsterbountyserver.entity.Habitat;
import org.example.monsterbountyserver.repository.CreatureRepository;
import org.example.monsterbountyserver.repository.HabitatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureRepository creatureRepository;
    private final HabitatRepository habitatRepository;

    public CreatureController(CreatureRepository creatureRepository, HabitatRepository habitatRepository) {
        this.creatureRepository = creatureRepository;
        this.habitatRepository = habitatRepository;
    }

    @GetMapping
    public List<Creature> getAllCreatures() {
        return creatureRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Creature> getCreatureById(@PathVariable Long id) {
        Optional<Creature> creature = creatureRepository.findById(id);

        if (creature.isPresent()) {
            return ResponseEntity.ok(creature.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Creature> createCreature(@RequestBody Map<String, String> body) {

        Habitat habitat = habitatRepository.findAll().stream().findFirst().orElseGet(() -> {
            Habitat h = new Habitat();
            h.setBiome("FOREST");
            h.setLocation("Default Habitat");
            h.setMinTempC(10);
            h.setMaxTempC(25);
            h.setCreatedAt(LocalDateTime.now());
            return habitatRepository.save(h);
        });

        Creature creature = new Creature();
        creature.setName(body.get("name"));
        creature.setSpecies(body.get("species"));
        creature.setDangerLevel(body.get("dangerLevel"));
        creature.setCondition(body.get("condition"));
        creature.setNotes(body.get("notes"));
        creature.setCreatedAt(LocalDateTime.now());
        creature.setHabitat(habitat);

        Creature savedCreature = creatureRepository.save(creature);

        return ResponseEntity.status(201).body(savedCreature);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Creature> updateCreature(@PathVariable Long id, @RequestBody Creature updated) {

        Optional<Creature> creatureOpt = creatureRepository.findById(id);

        if (creatureOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Creature creature = creatureOpt.get();
        creature.setName(updated.getName());
        creature.setSpecies(updated.getSpecies());
        creature.setDangerLevel(updated.getDangerLevel());
        creature.setCondition(updated.getCondition());
        creature.setNotes(updated.getNotes());

        return ResponseEntity.ok(creatureRepository.save(creature));
    }
}