package com.vetimeline.api.application.customer.status;

import com.vetimeline.api.domain.customer.Customer;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.customer.CustomerStatus;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;

import java.util.Objects;
import java.util.UUID;

public class UpdatCustomerStatusHandler {
    private final CustomerRepository customerRepository;

    public UpdatCustomerStatusHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public UpdateCustomerStatusResponse execute(UpdateCustomerStatusCommand command) throws EntityNotFound, Forbidden {
        Customer customer = customerRepository.find(UUID.fromString(command.getId()));
        ensureCustomerIsFromSameOrganization(customer, command.getOrganization());
        customer.updateStatus(CustomerStatus.valueOf(command.getStatus()));
        customerRepository.save(customer);

        return new UpdateCustomerStatusResponse(customer);
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
