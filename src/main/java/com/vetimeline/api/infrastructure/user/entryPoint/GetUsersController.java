package com.vetimeline.api.infrastructure.user.entryPoint;

import com.vetimeline.api.application.user.getUsers.GetUsersCommand;
import com.vetimeline.api.application.user.getUsers.GetUsersHandler;
import com.vetimeline.api.application.user.getUsers.GetUsersResponse;
import com.vetimeline.api.domain.user.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUsersController {
    private final UserRepository userRepository;

    public GetUsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "users")
    public GetUsersResponse GetUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        GetUsersHandler handler = new GetUsersHandler(userRepository);
        GetUsersCommand command = new GetUsersCommand(page, limit);

        return handler.execute(command);
    }
}
