package com.vetimeline.api.application.user.createToken;

public class CreateTokenCommand {
    private String email;
    private String password;

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
