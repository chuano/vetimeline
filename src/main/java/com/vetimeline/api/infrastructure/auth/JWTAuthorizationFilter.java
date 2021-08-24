package com.vetimeline.api.infrastructure.auth;

import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET;
    private final UserRepository userRepository;

    // Inject appSecret for jwt signature and user repository
    public JWTAuthorizationFilter(String appSecret, UserRepository userRepository) {
        this.SECRET = appSecret;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        try {
            if (checkJWTToken(request)) {
                Claims claims = validateToken(request);
                setUpSpringAuthentication(claims);
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (EntityNotFound | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            Logger logger = LoggerFactory.getLogger("KJ");
            logger.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(this.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) throws EntityNotFound {
        User user = this.userRepository.find(UUID.fromString(claims.get("id").toString()));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getId(), user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean checkJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
    }
}
