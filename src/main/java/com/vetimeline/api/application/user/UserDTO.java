package com.vetimeline.api.application.user;

import com.vetimeline.api.domain.user.CompleteName;
import com.vetimeline.api.domain.user.User;

public class UserDTO {
    public String id;
    public String firstName;
    public String firstSurname;
    public String secondSurname;
    public String email;
    public String organization;
    public String status;

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
}
