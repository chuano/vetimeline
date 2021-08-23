package com.vetimeline.api.infrastructure.auth.entryPoint;

import com.vetimeline.api.application.user.createToken.CreateTokenCommand;
import com.vetimeline.api.application.user.createToken.CreateTokenHandler;
import com.vetimeline.api.application.user.createToken.CreateTokenResponse;
import com.vetimeline.api.domain.auth.PasswordEncoder;
import com.vetimeline.api.domain.shared.Unauthorized;
import com.vetimeline.api.domain.user.UserNotFound;
import com.vetimeline.api.domain.user.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTokenController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateTokenController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.POST, value = "auth/token")
    public CreateTokenResponse createToken(
            @RequestBody() CreateTokenCommand command
    ) throws UserNotFound, Unauthorized {
        CreateTokenHandler handler = new CreateTokenHandler(userRepository, passwordEncoder);
        return handler.execute(command);
    }
}
