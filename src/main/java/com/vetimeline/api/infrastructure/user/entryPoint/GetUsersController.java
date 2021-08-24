package com.vetimeline.api.infrastructure.user.entryPoint;

import com.vetimeline.api.application.user.getUsers.GetUsersCommand;
import com.vetimeline.api.application.user.getUsers.GetUsersHandler;
import com.vetimeline.api.application.user.getUsers.GetUsersResponse;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUsersController {
    private final UserRepository userRepository;

    public GetUsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/v1/users")
    public GetUsersResponse GetUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            Authentication authentication
    ) {
        User user = (User) authentication.getCredentials();
        GetUsersHandler handler = new GetUsersHandler(userRepository);
        GetUsersCommand command = new GetUsersCommand(page, limit, user.getOrganization().toString());

        return handler.execute(command);
    }
}
