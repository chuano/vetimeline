package com.vetimeline.api.domain.customer;

import com.vetimeline.api.domain.shared.EntityNotFound;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface CustomerRepository {
    Customer find(UUID id) throws EntityNotFound;

    Customer findOneBy(HashMap<String, Object> criteria) throws EntityNotFound;

    List<Customer> findBy(HashMap<String, Object> criteria, Integer page, Integer limit);

    List<Customer> findAll(Integer page, Integer limit);

    void save(Customer customer);

}
