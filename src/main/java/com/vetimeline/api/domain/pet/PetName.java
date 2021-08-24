package com.vetimeline.api.domain.pet;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PetName {
    @Column(name = "name")
    private String value;

    public PetName() {
    }

    public PetName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
