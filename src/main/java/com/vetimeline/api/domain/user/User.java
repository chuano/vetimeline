package com.vetimeline.api.domain.user;

import com.vetimeline.api.domain.shared.EmailAddress;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    @Embedded
    private CompleteName name;
    @Embedded
    private EmailAddress email;
    @Column(name = "organization")
    private UUID organization;
    @Embedded
    private Password password;
    @Column(name = "status")
    private UserStatus status;

    private User() {
    }

    public User(UUID id, CompleteName name, EmailAddress email, UUID organization, Password password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.organization = organization;
        this.password = password;
        this.status = UserStatus.ACTIVE;
    }

    public UUID getId() {
        return id;
    }

    public CompleteName getName() {
        return name;
    }

    public UUID getOrganization() {
        return organization;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public UserStatus getStatus() {
        return status;
    }
}
