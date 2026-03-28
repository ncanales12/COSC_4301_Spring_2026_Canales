/*
 * CreatureController
 *
 * This REST controller handles HTTP requests for the Creature API.
 * It provides endpoints to:
 * - Retrieve all creatures
 * - Retrieve a creature by ID
 * - Create a new creature
 * - Update an existing creature
 * - Delete a creature
 *
 * The controller interacts with the CreatureRepository and HabitatRepository
 * to read and write data to the PostgreSQL database.
 *
 * All endpoints return JSON responses and use appropriate HTTP status codes.
 */

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

    /*
     * GET /api/creatures
     * Returns a list of all creatures
     */
    @GetMapping
    public List<Creature> getAllCreatures() {
        return creatureRepository.findAll();
    }

    /*
     * GET /api/creatures/{id}
     * Returns a single creature by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Creature> getCreatureById(@PathVariable Long id) {

        Optional<Creature> creature = creatureRepository.findById(id);

        if (creature.isPresent()) {
            return ResponseEntity.ok(creature.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * POST /api/creatures
     * Creates a new creature.
     * If no habitat exists, a default habitat is automatically created.
     */
    @PostMapping
    public ResponseEntity<Creature> createCreature(@RequestBody Map<String, String> body) {

        // Validate required fields
        if (body.get("name") == null || body.get("name").isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }

        if (body.get("species") == null || body.get("species").isBlank()) {
            throw new IllegalArgumentException("Species is required.");
        }

        if (body.get("dangerLevel") == null || body.get("dangerLevel").isBlank()) {
            throw new IllegalArgumentException("Danger level is required.");
        }

        if (body.get("condition") == null || body.get("condition").isBlank()) {
            throw new IllegalArgumentException("Condition is required.");
        }

        // Use existing habitat or create a default one
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

    /*
     * PUT /api/creatures/{id}
     * Updates an existing creature
     */
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

        Creature savedCreature = creatureRepository.save(creature);

        return ResponseEntity.ok(savedCreature);
    }

    /*
     * DELETE /api/creatures/{id}
     * Deletes a creature
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreature(@PathVariable Long id) {

        Optional<Creature> creatureOpt = creatureRepository.findById(id);

        if (creatureOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        creatureRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}