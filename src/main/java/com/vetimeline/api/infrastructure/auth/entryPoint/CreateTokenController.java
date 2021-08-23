package com.vetimeline.api.infrastructure.auth.entryPoint;

import com.vetimeline.api.application.auth.createToken.CreateTokenCommand;
import com.vetimeline.api.application.auth.createToken.CreateTokenHandler;
import com.vetimeline.api.application.auth.createToken.CreateTokenResponse;
import com.vetimeline.api.domain.auth.JwtManager;
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
    private final JwtManager jwtManager;

    public CreateTokenController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtManager jwtManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtManager = jwtManager;
    }

    @RequestMapping(method = RequestMethod.POST, value = "auth/token")
    public CreateTokenResponse createToken(
            @RequestBody() CreateTokenCommand command
    ) throws UserNotFound, Unauthorized {
        CreateTokenHandler handler = new CreateTokenHandler(userRepository, passwordEncoder, jwtManager);
        return handler.execute(command);
    }
}
