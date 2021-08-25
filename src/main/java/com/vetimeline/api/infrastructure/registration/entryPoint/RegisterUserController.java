package com.vetimeline.api.infrastructure.registration.entryPoint;

import com.vetimeline.api.application.registration.register.*;
import com.vetimeline.api.domain.auth.PasswordEncoder;
import com.vetimeline.api.domain.organization.OrganizationRepository;
import com.vetimeline.api.domain.user.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RegisterUserController {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserController(
            OrganizationRepository organizationRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/v1/registrations", method = RequestMethod.POST)
    public RegisterUserResponse register(
            @RequestBody() RegisterUserRequest request
    ) throws OrganizationAlreadyRegistered, EmailAlreadyInUse {
        RegisterUserCommand command = new RegisterUserCommand(
                request.organizationName,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                request.firstName,
                request.firstSurname,
                request.secondSurname,
                request.email,
                request.password
        );
        RegisterUserHandler handler = new RegisterUserHandler(organizationRepository, userRepository, passwordEncoder);
        return handler.execute(command);
    }
}
