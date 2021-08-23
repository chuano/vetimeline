package com.vetimeline.api.infrastructure.user.persistence;

import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserNotFound;
import com.vetimeline.api.domain.user.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll(Integer page, Integer limit) {
        return entityManager.createQuery("SELECT u FROM User u")
                            .setMaxResults(limit)
                            .setFirstResult((int) Math.ceil((page - 1) * limit))
                            .getResultList();
    }

    @Override
    public User find(UUID id) throws UserNotFound {
        try {
            return (User) entityManager
                    .createQuery("SELECT u FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new UserNotFound();
        }
    }

    @Override
    public User findByEmail(EmailAddress email) throws UserNotFound {
        try {
            return (User) entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            throw new UserNotFound();
        }
    }
}
