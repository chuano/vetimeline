package com.vetimeline.api.domain.shared;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface GenericRepository<T> {
    T find(UUID id) throws EntityNotFound;

    T findOneBy(HashMap<String, Object> criteria) throws EntityNotFound;

    List<T> findBy(HashMap<String, Object> criteria, Integer page, Integer limit);

    List<T> findAll(Integer page, Integer limit);

    void save(T customer);
}
