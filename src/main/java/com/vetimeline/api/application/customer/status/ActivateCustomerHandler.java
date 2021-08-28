package com.vetimeline.api.application.customer.status;

import com.vetimeline.api.domain.customer.Customer;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;

import java.util.Objects;
import java.util.UUID;

public class ActivateCustomerHandler {
    private final CustomerRepository customerRepository;

    public ActivateCustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ActivateCustomerResponse execute(ActivateCustomerCommand command) throws EntityNotFound, Forbidden {
        Customer customer = customerRepository.find(UUID.fromString(command.getId()));
        ensureCustomerIsFromSameOrganization(customer, command.getOrganization());
        customer.activate();
        customerRepository.save(customer);

        return new ActivateCustomerResponse(customer);
    }

    private void ensureCustomerIsFromSameOrganization(
            Customer customer,
            String organization
    ) throws Forbidden {
        if (!Objects.equals(customer.getOrganization().toString(), organization)) {
            throw new Forbidden("Forbidden customer update");
        }
    }
}
