package com.vetimeline.api.domain.user;

import com.vetimeline.api.domain.shared.EmailAddress;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    List<User> findAll(Integer page, Integer limit);

    User find(UUID id) throws UserNotFound;

    User findByEmail(EmailAddress email) throws UserNotFound;

    void save(User user);
}
