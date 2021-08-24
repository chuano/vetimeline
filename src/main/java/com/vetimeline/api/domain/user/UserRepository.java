package com.vetimeline.api.domain.user;

import com.vetimeline.api.domain.shared.EntityNotFound;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User find(UUID id) throws EntityNotFound;

    User findOneBy(HashMap<String, Object> criteria) throws EntityNotFound;

    List<User> findBy(HashMap<String, Object> criteria, Integer page, Integer limit);

    List<User> findAll(Integer page, Integer limit);

    void save(User user);
}
