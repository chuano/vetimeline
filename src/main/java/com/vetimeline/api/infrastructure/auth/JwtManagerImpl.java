package com.vetimeline.api.infrastructure.auth;

import com.vetimeline.api.domain.auth.JwtManager;
import com.vetimeline.api.domain.auth.JwtPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtManagerImpl implements JwtManager {
    private final String tokenSignature;

    public JwtManagerImpl(@Value("${appConfig.appSecret}") String tokenSignature) {
        this.tokenSignature = tokenSignature;
    }

    @Override
    public String encode(JwtPayload payload) {
        // 20 years token duration
        long duration = 630720000000L;

        return Jwts
                .builder()
                .claim("id", payload.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration))
                .signWith(SignatureAlgorithm.HS512, tokenSignature.getBytes())
                .compact();
    }

    @Override
    public JwtPayload decode(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSignature.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return new JwtPayload(claims.get("id").toString());
    }
}
