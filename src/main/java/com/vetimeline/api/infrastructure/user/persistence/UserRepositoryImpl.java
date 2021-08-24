package com.vetimeline.api.infrastructure.user.persistence;

import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserRepository;
import com.vetimeline.api.infrastructure.shared.persistence.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
