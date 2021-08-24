package com.vetimeline.api.domain.pet;

import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Pet {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Embedded
    private PetName name;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID customer;

    protected Pet() {
    }

    public Pet(UUID id, PetName name, UUID customer) {
        this.id = id;
        this.name = name;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public PetName getName() {
        return name;
    }

    public UUID getCustomer() {
        return customer;
    }
}
