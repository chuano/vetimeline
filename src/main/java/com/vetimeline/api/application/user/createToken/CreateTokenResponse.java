package com.vetimeline.api.application.user.createToken;

public class CreateTokenResponse {
    private String token;

    public CreateTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
