/*
 * Creature entity maps to the creatures table in PostgreSQL.
 */

package org.example.monsterbountyserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
 * Creature entity represents a monster tracked by the system.
 * Each creature belongs to a habitat and contains information
 * such as species, danger level, condition, and notes.
 */
@Entity
@Table(name = "creatures")
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species;

    @Column(name = "danger_level")
    private String dangerLevel;

    private String condition;

    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "habitat_id")
    private Habitat habitat;

    public Creature() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    public String getCondition() {
        return condition;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }
}