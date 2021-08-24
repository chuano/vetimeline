package com.vetimeline.api.application.customer.get;

import com.vetimeline.api.domain.customer.CustomerRepository;

import java.util.HashMap;
import java.util.UUID;

public class GetCustomersHandler {
    private final CustomerRepository customerRepository;

    public GetCustomersHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public GetCustomersResponse execute(GetCustomersCommand command) {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("organization", UUID.fromString(command.getOrganization()));

        return new GetCustomersResponse(
                customerRepository.findBy(criteria, command.getPage(), command.getLimit()),
                command.getPage(),
                command.getLimit()
        );
    }
}
