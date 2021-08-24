package com.vetimeline.api.application.customer.create;

import com.vetimeline.api.domain.customer.*;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.PhoneNumber;

import java.util.UUID;

public class CreateCustomerHandler {
    private final CustomerRepository customerRepository;

    public CreateCustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CreateCustomerResponse execute(CreateCustomerCommand command) {
        Customer customer = createCustomer(command);
        customerRepository.save(customer);

        return new CreateCustomerResponse(customer);
    }

    private Customer createCustomer(CreateCustomerCommand command) {
        return new Customer(
                UUID.fromString(command.getId()),
                new CustomerName(command.getFirstName(), command.getFirstSurname(), command.getSecondSurname()),
                new IdDocument(command.getIdDocument()),
                new PhoneNumber(command.getPhone()),
                new EmailAddress(command.getEmail()),
                new CustomerAddress(command.getAddress(), command.getCity(), command.getZipCode()),
                UUID.fromString(command.getOrganization())
        );
    }
}
