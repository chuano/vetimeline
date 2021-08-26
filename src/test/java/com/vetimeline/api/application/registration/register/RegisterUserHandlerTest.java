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
import com.vetimeline.api.infrastructure.auth.PasswordEncoderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RegisterUserHandlerTest {
    private OrganizationRepository organizationRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        organizationRepository = mock(OrganizationRepository.class);
        userRepository = mock(UserRepository.class);
        passwordEncoder = new PasswordEncoderImpl();
    }

    @Test
    public void ShouldRegisterNewOrganizationAndUser() throws OrganizationAlreadyRegistered, EmailAlreadyInUse {
        when(organizationRepository.findBy(any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        when(userRepository.findBy(any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        RegisterUserCommand command = getRegisterUserCommand();
        RegisterUserHandler handler = new RegisterUserHandler(organizationRepository, userRepository, passwordEncoder);
        RegisterUserResponse response = handler.execute(command);

        assertTrue(new ReflectionEquals(getExpected()).matches(response));
        verify(organizationRepository).save(any(Organization.class));
        verify(userRepository).save(any(User.class));
    }


    @Test
    public void ShouldThrowOrganizationAlreadyExists() {
        ArrayList<Organization> organizations = new ArrayList<>();
        organizations.add(createOrganization());
        when(organizationRepository.findBy(any(), anyInt(), anyInt())).thenReturn(organizations);

        RegisterUserCommand command = getRegisterUserCommand();
        RegisterUserHandler handler = new RegisterUserHandler(organizationRepository, userRepository, passwordEncoder);
        assertThrows(OrganizationAlreadyRegistered.class,() -> handler.execute(command));
    }

    @Test
    public void ShouldThrowEmailAlreadyInUse() {
        ArrayList<User> users = new ArrayList<>();
        users.add(createUser());
        when(organizationRepository.findBy(any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        when(userRepository.findBy(any(), anyInt(), anyInt())).thenReturn(users);

        RegisterUserCommand command = getRegisterUserCommand();
        RegisterUserHandler handler = new RegisterUserHandler(organizationRepository, userRepository, passwordEncoder);
        assertThrows(EmailAlreadyInUse.class,() -> handler.execute(command));
    }

    private RegisterUserCommand getRegisterUserCommand() {
        return new RegisterUserCommand(
                "Test Organization",
                "1064202f-4787-4fd7-a0b4-1d4b0ccb5f69",
                "cf469ed2-6785-48b0-8358-7c5a7a219507",
                "FirstName",
                "FirstSurname",
                "SecondSurname",
                "email@domain.com",
                "password"
        );
    }

    private RegisterUserResponse getExpected() {
        Organization organization = createOrganization();
        User user = createUser();
        return new RegisterUserResponse(organization, user);
    }

    private Organization createOrganization() {
        return new Organization(
                UUID.fromString("1064202f-4787-4fd7-a0b4-1d4b0ccb5f69"),
                new OrganizationName("Test Organization")
        );
    }

    private User createUser() {
        return new User(
                UUID.fromString("cf469ed2-6785-48b0-8358-7c5a7a219507"),
                new CompleteName("FirstName", "FirstSurname", "SecondSurname"),
                new EmailAddress("email@domain.com"),
                UUID.fromString("1064202f-4787-4fd7-a0b4-1d4b0ccb5f69"),
                new Password("password")
        );
    }
}