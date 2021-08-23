package com.vetimeline.api.application.user;

import com.vetimeline.api.domain.user.User;

public class UserDTO {
    private final String id;
    private final String firstName;
    private final String firstSurname;
    private final String secondSurname;
    private final String email;
    private final String organization;
    private final String status;

    public UserDTO(String id, String firstName, String firstSurname, String secondSurname, String email,
                   String organization, String status) {
        this.id = id;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.email = email;
        this.organization = organization;
        this.status = status;
    }

    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getId().toString(),
                user.getName().getFirstName(),
                user.getName().getFirstSurname(),
                user.getName().getSecondSurname(),
                user.getEmail().getValue(),
                user.getOrganization().toString(),
                user.getStatus().toString()
        );
    }

    public String getId() {
        return id;
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

    public String getOrganization() {
        return organization;
    }

    public String getStatus() {
        return status;
    }
}
