package com.vetimeline.api.domain.pet;

import com.vetimeline.api.domain.shared.EntityNotFound;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface PetRepository {
    Pet find(UUID id) throws EntityNotFound;

    Pet findOneBy(HashMap<String, Object> criteria) throws EntityNotFound;

    List<Pet> findBy(HashMap<String, Object> criteria, Integer page, Integer limit);

    List<Pet> findAll(Integer page, Integer limit);

    void save(Pet customer);
}
