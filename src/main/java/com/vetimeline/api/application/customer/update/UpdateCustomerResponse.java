package com.vetimeline.api.application.customer.update;

import com.vetimeline.api.application.customer.CustomerDTO;
import com.vetimeline.api.domain.customer.Customer;

public class UpdateCustomerResponse extends CustomerDTO {
    public UpdateCustomerResponse(Customer customer) {
        super(
                customer.getId().toString(),
                customer.getName().getFirstName(),
                customer.getName().getFirstSurname(),
                customer.getName().getSecondSurname(),
                null != customer.getIdDocument() ? customer.getIdDocument().getValue() : null,
                null != customer.getPhone() ? customer.getPhone().getValue() : null,
                null != customer.getAddress() ? customer.getAddress().getAddress() : null,
                null != customer.getAddress() ? customer.getAddress().getCity() : null,
                null != customer.getAddress() ? customer.getAddress().getZipCode() : null,
                customer.getStatus().toString()
        );
    }
}
