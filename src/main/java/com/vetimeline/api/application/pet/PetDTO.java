package com.vetimeline.api.application.pet;

import com.vetimeline.api.domain.pet.Pet;

public class PetDTO {
    private final String id;
    private final String name;

    public PetDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PetDTO fromEntity(Pet pet) {
        return new PetDTO(pet.getId().toString(), pet.getName().getValue());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
