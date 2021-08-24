package com.vetimeline.api.application.auth.createToken;

import com.vetimeline.api.domain.auth.JwtManager;
import com.vetimeline.api.domain.auth.JwtPayload;
import com.vetimeline.api.domain.auth.PasswordEncoder;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Unauthorized;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserRepository;

import java.util.HashMap;

public class CreateTokenHandler {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;

    public CreateTokenHandler(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtManager jwtManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtManager = jwtManager;
    }

    public CreateTokenResponse execute(CreateTokenCommand command) throws EntityNotFound, Unauthorized {
        User user = getUser(new EmailAddress(command.getEmail()));
        if (!passwordEncoder.verify(user.getPassword(), command.getPassword())) {
            throw new Unauthorized();
        }

        JwtPayload payload = new JwtPayload(user.getId().toString());
        return new CreateTokenResponse(jwtManager.encode(payload));
    }

    private User getUser(EmailAddress emailAddress) throws EntityNotFound {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("email", emailAddress);
        return userRepository.findOneBy(criteria);
    }
}
