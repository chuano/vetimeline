package com.vetimeline.api.application.customer.create;

import com.vetimeline.api.domain.customer.*;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.PhoneNumber;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CreateCustomerHandler {
    private final CustomerRepository customerRepository;

    public CreateCustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CreateCustomerResponse execute(CreateCustomerCommand command) throws IdDocumentAlreadyExists {
        ensureIdDocumentDoesNotExists(command);
        Customer customer = createCustomer(command);
        customerRepository.save(customer);

        return new CreateCustomerResponse(customer);
    }

    private void ensureIdDocumentDoesNotExists(CreateCustomerCommand command) throws IdDocumentAlreadyExists {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("idDocument", new IdDocument(command.getIdDocument()));
        List<Customer> customers = customerRepository.findBy(criteria, 1, 1);
        if (customers.size() > 0) {
            throw new IdDocumentAlreadyExists();
        }
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
