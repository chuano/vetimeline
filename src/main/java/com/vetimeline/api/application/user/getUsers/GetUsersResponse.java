package com.vetimeline.api.application.user.getUsers;

import com.vetimeline.api.domain.shared.ListResponse;
import com.vetimeline.api.domain.user.User;

import java.util.List;

public class GetUsersResponse extends ListResponse<User> {
    public GetUsersResponse(List<User> data, Integer page, Integer limit) {
        super(data, page, limit);
    }
}
