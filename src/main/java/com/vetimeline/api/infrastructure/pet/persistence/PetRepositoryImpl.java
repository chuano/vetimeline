package com.vetimeline.api.infrastructure.pet.persistence;

import com.vetimeline.api.domain.pet.Pet;
import com.vetimeline.api.domain.pet.PetRepository;
import com.vetimeline.api.infrastructure.shared.persistence.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class PetRepositoryImpl extends GenericRepositoryImpl<Pet> implements PetRepository {
    public PetRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Pet.class);
    }
}
