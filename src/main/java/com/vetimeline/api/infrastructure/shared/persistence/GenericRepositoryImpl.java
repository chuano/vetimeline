package com.vetimeline.api.infrastructure.shared.persistence;

import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.GenericRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class GenericRepositoryImpl<T> implements GenericRepository<T> {
    protected final EntityManager entityManager;
    private final Class<T> entityClass;

    public GenericRepositoryImpl(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public T find(UUID id) throws EntityNotFound {
        T item = entityManager.find(this.entityClass, id);
        if (null == item) {
            throw new EntityNotFound();
        }

        return item;
    }

    public T findOneBy(HashMap<String, Object> criteria) throws EntityNotFound {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(this.entityClass);
            Root<T> queryRoot = query.from(this.entityClass);
            Predicate[] predicates = getPredicates(criteria, criteriaBuilder, queryRoot);
            query.select(queryRoot).where(predicates);

            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new EntityNotFound();
        }
    }

    public List<T> findBy(HashMap<String, Object> criteria, Integer page, Integer limit) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(this.entityClass);
            Root<T> queryRoot = query.from(this.entityClass);
            Predicate[] predicates = getPredicates(criteria, criteriaBuilder, queryRoot);
            query.select(queryRoot).where(predicates);

            return entityManager.createQuery(query)
                    .setMaxResults(limit)
                    .setFirstResult((page - 1) * limit)
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<T> findAll(Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(this.entityClass);
        Root<T> queryRoot = query.from(this.entityClass);
        query.select(queryRoot);

        return entityManager.createQuery(query)
                .setMaxResults(limit)
                .setFirstResult((page - 1) * limit)
                .getResultList();
    }

    @Transactional
    public void save(T item) {
        entityManager.persist(item);
        entityManager.flush();
    }

    private Predicate[] getPredicates(HashMap<String, Object> criteria, CriteriaBuilder criteriaBuilder,
                                      Root<T> queryRoot) {
        Predicate[] predicates = new Predicate[criteria.size()];

        int index = 0;
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            predicates[index] = criteriaBuilder.equal(queryRoot.get(entry.getKey()), entry.getValue());
            index++;
        }

        return predicates;
    }
}
