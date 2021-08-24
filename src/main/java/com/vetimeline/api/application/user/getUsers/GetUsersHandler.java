package com.vetimeline.api.application.user.getUsers;

import com.vetimeline.api.domain.user.UserRepository;

import java.util.HashMap;
import java.util.UUID;

public class GetUsersHandler {
    private final UserRepository userRepository;

    public GetUsersHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GetUsersResponse execute(GetUsersCommand command) {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("organization", UUID.fromString(command.getOrganization()));
        return new GetUsersResponse(
                userRepository.findBy(criteria, command.getPage(), command.getLimit()),
                command.getPage(),
                command.getLimit()
        );
    }
}
