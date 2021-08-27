package com.vetimeline.api.application.customer.upate;

import com.vetimeline.api.domain.customer.*;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;
import com.vetimeline.api.domain.shared.PhoneNumber;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UpdateCustomerHandler {
    private final CustomerRepository customerRepository;

    public UpdateCustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public UpdateCustomerResponse execute(
            UpdateCustomerCommand command
    ) throws CustomerNotFound, IdDocumentAlreadyExists, EntityNotFound, Forbidden {
        Customer customer = customerRepository.find(UUID.fromString(command.getId()));
        ensureIdDocumentDoesNotExists(customer, command.getIdDocument());
        ensureCustomerIsFromSameOrganization(customer, command.getOrganization());

        customer.update(
                new CustomerName(command.getFirstName(), command.getFirstSurname(), command.getSecondSurname()),
                new IdDocument(command.getIdDocument()),
                new PhoneNumber(command.getPhone()),
                new EmailAddress(command.getEmail()),
                new CustomerAddress(command.getAddress(), command.getCity(), command.getZipCode())
        );
        customerRepository.save(customer);

        return new UpdateCustomerResponse(customer);
    }

    private void ensureCustomerIsFromSameOrganization(
            Customer customer,
            String organization
    ) throws Forbidden {
        if (!Objects.equals(customer.getOrganization().toString(), organization)) {
            throw new Forbidden("Forbidden customer update");
        }
    }

    private void ensureIdDocumentDoesNotExists(Customer customer, String idDocument) throws IdDocumentAlreadyExists {
        IdDocument idDocumentObject = new IdDocument(idDocument);
        if (customer.getIdDocument().equals(idDocumentObject)) {
            return;
        }

        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("idDocument", new IdDocument(idDocument));
        List<Customer> customers = customerRepository.findBy(criteria, 1, 1);
        if (customers.size() > 0) {
            throw new IdDocumentAlreadyExists();
        }
    }
}
