package com.vetimeline.api.domain.auth;

public interface JwtManager {
    String encode(JwtPayload payload);

    JwtPayload decode(String token);
}
