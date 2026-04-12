package org.example.monsterbountyserver.controller;

import org.example.monsterbountyserver.dto.CreatureRequest;
import org.example.monsterbountyserver.dto.CreatureResponse;
import org.example.monsterbountyserver.service.CreatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureService creatureService;

    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    @GetMapping
    public List<CreatureResponse> getAllCreatures() {
        return creatureService.getAllCreatures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatureResponse> getCreatureById(@PathVariable Long id) {
        Optional<CreatureResponse> creature = creatureService.getCreatureById(id);

        if (creature.isPresent()) {
            return ResponseEntity.ok(creature.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreatureResponse> createCreature(@RequestBody CreatureRequest request) {
        CreatureResponse response = creatureService.createCreature(request);
        return ResponseEntity.status(201).body(response);
    }
}