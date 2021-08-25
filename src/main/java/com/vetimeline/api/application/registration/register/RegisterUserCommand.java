package com.vetimeline.api.application.registration.register;

public class RegisterUserCommand {
    private final String organizationName;
    private final String organizationId;
    private final String userId;
    private final String firstName;
    private final String firstSurname;
    private final String secondSurname;
    private final String email;
    private final String password;

    public RegisterUserCommand(String organizationName, String organizationId, String userId, String firstName,
                               String firstSurname, String secondSurname, String email, String password) {
        this.organizationName = organizationName;
        this.organizationId = organizationId;
        this.userId = userId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.email = email;
        this.password = password;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOrganizationId() {
        return organizationId;
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

    public String getPassword() {
        return password;
    }
}
