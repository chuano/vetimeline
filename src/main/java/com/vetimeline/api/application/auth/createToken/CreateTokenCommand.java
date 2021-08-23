package com.vetimeline.api.application.auth.createToken;

public class CreateTokenCommand {
    private final String email;
    private final String password;

    public CreateTokenCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
