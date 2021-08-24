package com.vetimeline.api.domain.organization;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Organization {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private final UUID id;
    private final String name;

    public Organization(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
