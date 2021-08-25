package com.vetimeline.api.application.registration;

import com.vetimeline.api.domain.organization.Organization;
import com.vetimeline.api.domain.user.User;

public class RegistrationDTO {
    private final String organizationId;
    private final String organizationName;
    private final String userId;
    private final String firstName;
    private final String firstSurname;
    private final String secondSurname;
    private final String email;
    private final String status;

    public RegistrationDTO(String organizationId, String organizationName, String userId, String firstName,
                           String firstSurname, String secondSurname, String email, String status) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.userId = userId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.email = email;
        this.status = status;
    }

    public static RegistrationDTO fromEntity(Organization organization, User user) {
        return new RegistrationDTO(
                organization.getId().toString(),
                organization.getName().getValue(),
                user.getId().toString(),
                user.getName().getFirstName(),
                user.getName().getFirstSurname(),
                user.getName().getSecondSurname(),
                user.getEmail().getValue(),
                user.getStatus().toString()
        );
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}
