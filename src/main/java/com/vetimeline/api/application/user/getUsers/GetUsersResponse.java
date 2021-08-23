package com.vetimeline.api.application.user.getUsers;

import com.vetimeline.api.application.user.UserDTO;
import com.vetimeline.api.domain.shared.ListResponse;
import com.vetimeline.api.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class GetUsersResponse extends ListResponse<UserDTO> {
    public GetUsersResponse(List<User> data, Integer page, Integer limit) {
        super(
                data.stream()
                        .map(UserDTO::fromEntity)
                        .collect(Collectors.toList()),
                page,
                limit
        );
    }
}
