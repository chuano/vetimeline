package com.vetimeline.api.infrastructure.registration.entryPoint;

public class RegisterUserRequest {
    public String organizationName;
    public String firstName;
    public String firstSurname;
    public String secondSurname;
    public String email;
    public String password;

    public RegisterUserRequest(String organizationName, String firstName, String firstSurname,
                               String secondSurname, String email, String password) {
        this.organizationName = organizationName;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.email = email;
        this.password = password;
    }
}
