package com.vetimeline.api.application.registration.register;

import com.vetimeline.api.application.registration.RegistrationDTO;
import com.vetimeline.api.domain.organization.Organization;
import com.vetimeline.api.domain.user.User;

public class RegisterUserResponse extends RegistrationDTO {
    public RegisterUserResponse(Organization organization, User user) {
        super(
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
}
