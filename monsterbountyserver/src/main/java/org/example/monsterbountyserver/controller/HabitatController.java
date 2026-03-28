package org.example.monsterbountyserver.controller;

import org.example.monsterbountyserver.entity.Habitat;
import org.example.monsterbountyserver.repository.HabitatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
 * HabitatController
 *
 * This REST controller handles HTTP requests for the Habitat API.
 * It provides endpoints to:
 * - Retrieve all habitats
 * - Retrieve a habitat by ID
 *
 * The controller interacts with the HabitatRepository to read data
 * from the PostgreSQL database.
 *
 * All endpoints return JSON responses and use appropriate HTTP status codes.
 */
@RestController
@RequestMapping("/api/habitats")
public class HabitatController {

    private final HabitatRepository habitatRepository;

    public HabitatController(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }

    @GetMapping
    public List<Habitat> getAllHabitats() {
        return habitatRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitat> getHabitatById(@PathVariable Long id) {
        Optional<Habitat> habitat = habitatRepository.findById(id);

        if (habitat.isPresent()) {
            return ResponseEntity.ok(habitat.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}