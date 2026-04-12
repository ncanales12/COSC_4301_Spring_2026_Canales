package org.example.monsterbountyserver.dto;

import java.time.LocalDateTime;

public class CreatureResponse {

    public Long id;
    public String name;
    public String species;
    public String dangerLevel;
    public String condition;
    public String notes;
    public Long habitatId;
    public LocalDateTime createdAt;

}