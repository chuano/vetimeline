package com.vetimeline.api.application.user.getUsers;

import com.vetimeline.api.domain.user.UserRepository;

public class GetUsersHandler {
    private final UserRepository userRepository;

    public GetUsersHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GetUsersResponse execute(GetUsersCommand command) {
        return new GetUsersResponse(
                userRepository.findAll(command.getPage(), command.getLimit()),
                command.getPage(),
                command.getLimit()
        );
    }
}
