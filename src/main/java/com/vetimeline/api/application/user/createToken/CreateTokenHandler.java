package com.vetimeline.api.application.user.createToken;

import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.PasswordEncoder;
import com.vetimeline.api.domain.shared.Unauthorized;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserNotFound;
import com.vetimeline.api.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateTokenHandler {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateTokenHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateTokenResponse execute(CreateTokenCommand command) throws UserNotFound, Unauthorized {
        User user = userRepository.findByEmail(new EmailAddress(command.getEmail()));
        Logger logger = LoggerFactory.getLogger("9u89");
        logger.error(command.getEmail());
        logger.error(user.getName().getFirstName());
        if (!passwordEncoder.match(user.getPassword(), command.getPassword())) {
            throw new Unauthorized();
        }

        return new CreateTokenResponse("");
    }
}
