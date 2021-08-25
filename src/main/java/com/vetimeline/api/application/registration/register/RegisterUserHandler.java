package com.vetimeline.api.application.registration.register;

import com.vetimeline.api.domain.auth.PasswordEncoder;
import com.vetimeline.api.domain.organization.Organization;
import com.vetimeline.api.domain.organization.OrganizationName;
import com.vetimeline.api.domain.organization.OrganizationRepository;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.user.CompleteName;
import com.vetimeline.api.domain.user.Password;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RegisterUserHandler {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserHandler(
            OrganizationRepository organizationRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserResponse execute(
            RegisterUserCommand command
    ) throws OrganizationAlreadyRegistered, EmailAlreadyInUse {
        ensureDoesNotExistsOrganization(command.getOrganizationName());
        ensureEmailDoesNotExists(command.getEmail());

        Organization organization = new Organization(
                UUID.fromString(command.getOrganizationId()),
                new OrganizationName(command.getOrganizationName())
        );
        organizationRepository.save(organization);

        User user = new User(
                UUID.fromString(command.getUserId()),
                new CompleteName(command.getFirstName(), command.getFirstSurname(), command.getSecondSurname()),
                new EmailAddress(command.getEmail()),
                organization.getId(),
                new Password(passwordEncoder.encode(command.getPassword()))
        );
        userRepository.save(user);

        return new RegisterUserResponse(organization, user);
    }

    private void ensureEmailDoesNotExists(String email) throws EmailAlreadyInUse {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("email", new EmailAddress(email));
        List<User> users = userRepository.findBy(criteria, 1, 1);
        if (users.size() > 0) {
            throw new EmailAlreadyInUse();
        }
    }

    // TODO NO VA ESTA MIERDA
    private void ensureDoesNotExistsOrganization(String organizationName) throws OrganizationAlreadyRegistered {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("name", new OrganizationName(organizationName));
        List<Organization> organizations = organizationRepository.findBy(criteria, 1, 1);
        if (organizations.size() > 0) {
            throw new OrganizationAlreadyRegistered();
        }
    }
}
