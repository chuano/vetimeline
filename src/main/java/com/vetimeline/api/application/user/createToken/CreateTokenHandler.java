package com.vetimeline.api.application.user.createToken;

import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.auth.PasswordEncoder;
import com.vetimeline.api.domain.shared.Unauthorized;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserNotFound;
import com.vetimeline.api.domain.user.UserRepository;

public class CreateTokenHandler {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateTokenHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateTokenResponse execute(CreateTokenCommand command) throws UserNotFound, Unauthorized {
        User user = userRepository.findByEmail(new EmailAddress(command.getEmail()));
        if (!passwordEncoder.verify(user.getPassword(), command.getPassword())) {
            throw new Unauthorized();
        }

        return new CreateTokenResponse("");
    }
}
