package com.vetimeline.api.infrastructure.customer.persistence;

import com.vetimeline.api.domain.customer.Customer;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.infrastructure.shared.persistence.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CustomerRepositoryImpl extends GenericRepositoryImpl<Customer> implements CustomerRepository {
    public CustomerRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }
}
