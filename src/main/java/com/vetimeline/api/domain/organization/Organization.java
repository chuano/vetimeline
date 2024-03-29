package com.vetimeline.api.domain.organization;

import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Organization {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Embedded
    private OrganizationName name;

    protected Organization() {}

    public Organization(UUID id, OrganizationName name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public OrganizationName getName() {
        return name;
    }
}
