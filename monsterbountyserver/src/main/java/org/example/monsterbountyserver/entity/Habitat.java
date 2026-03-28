/*
 * Habitat entity maps to the habitats table in PostgreSQL.
 */

package org.example.monsterbountyserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habitats")

/*
 * Habitat entity represents the environment where creatures live.
 * It stores biome information, location, and temperature ranges.
 * Creatures reference a habitat through a foreign key relationship.
 */

public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biome;

    private String location;

    @Column(name = "min_temp_c")
    private Integer minTempC;

    @Column(name = "max_temp_c")
    private Integer maxTempC;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Habitat() {
    }

    public Long getId() {
        return id;
    }

    public String getBiome() {
        return biome;
    }

    public String getLocation() {
        return location;
    }

    public Integer getMinTempC() {
        return minTempC;
    }

    public Integer getMaxTempC() {
        return maxTempC;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMinTempC(Integer minTempC) {
        this.minTempC = minTempC;
    }

    public void setMaxTempC(Integer maxTempC) {
        this.maxTempC = maxTempC;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}