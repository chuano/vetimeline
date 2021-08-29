package com.vetimeline.api.application.customer.status;

import com.vetimeline.api.domain.customer.Customer;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;

import java.util.Objects;
import java.util.UUID;

public class DeactivateCustomerHandler {
    private final CustomerRepository customerRepository;

    public DeactivateCustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public DeactivateCustomerResponse execute(DeactivateCustomerCommand command) throws EntityNotFound, Forbidden {
        Customer customer = customerRepository.find(UUID.fromString(command.getId()));
        ensureCustomerIsFromSameOrganization(customer, command.getOrganization());
        customer.deactivate();
        customerRepository.save(customer);

        return new DeactivateCustomerResponse(customer);
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
