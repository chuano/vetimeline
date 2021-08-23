package com.vetimeline.api.application.auth.createToken;

public class CreateTokenResponse {
    private final String token;

    public CreateTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
