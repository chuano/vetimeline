package com.vetimeline.api.application.customer.upate;

import com.vetimeline.api.domain.customer.*;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;
import com.vetimeline.api.domain.shared.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UpdateCustomerHandlerTest {
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
    }

    @Test
    public void ShouldUpdateCustomer() throws EntityNotFound, IdDocumentAlreadyExists, CustomerNotFound, Forbidden {
        when(customerRepository.find(any())).thenReturn(getCustomer("7680ebe0-fc57-456e-9c60-f8307cf01ad7"));
        when(customerRepository.findBy(any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        UpdateCustomerCommand command = getCommand();
        UpdateCustomerHandler handler = new UpdateCustomerHandler(customerRepository);
        UpdateCustomerResponse response = handler.execute(command);

        assertTrue(new ReflectionEquals(getExpected()).matches(response));
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void ShouldThrowIdDocumentAlreadyExists() throws EntityNotFound {
        when(customerRepository.find(any())).thenReturn(getCustomer("7680ebe0-fc57-456e-9c60-f8307cf01ad7"));
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(getCustomer("7680ebe0-fc57-456e-9c60-f8307cf01ad7"));
        when(customerRepository.findBy(any(), anyInt(), anyInt())).thenReturn(customers);

        UpdateCustomerCommand command = getCommand();
        UpdateCustomerHandler handler = new UpdateCustomerHandler(customerRepository);

        assertThrows(IdDocumentAlreadyExists.class, () -> handler.execute(command));
    }

    @Test
    public void ShouldThrowForbiddenExceptionExists() throws EntityNotFound {
        when(customerRepository.find(any())).thenReturn(getCustomer("00000000-0000-0000-0000-000000000000"));
        when(customerRepository.findBy(any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        UpdateCustomerCommand command = getCommand();
        UpdateCustomerHandler handler = new UpdateCustomerHandler(customerRepository);

        assertThrows(Forbidden.class, () -> handler.execute(command));
    }

    private UpdateCustomerCommand getCommand() {
        return new UpdateCustomerCommand(
                "1499aab2-be02-487e-837d-3acb0e60dc32",
                "name2",
                "surname2",
                "secondSurname2",
                "44444442A",
                "666666662",
                "user@domain2.com",
                "address2",
                "city2",
                "00002",
                "7680ebe0-fc57-456e-9c60-f8307cf01ad7"
        );
    }

    private UpdateCustomerResponse getExpected() {
        return new UpdateCustomerResponse(getExpectedCustomer());
    }

    private Customer getCustomer(String organization) {
        return new Customer(
                UUID.fromString("1499aab2-be02-487e-837d-3acb0e60dc32"),
                new CustomerName("name", "surname", "secondSurname"),
                new IdDocument("44444444A"),
                new PhoneNumber("666666666"),
                new EmailAddress("user@domain.com"),
                new CustomerAddress("address", "city", "00000"),
                UUID.fromString(organization)
        );
    }

    private Customer getExpectedCustomer() {
        return new Customer(
                UUID.fromString("1499aab2-be02-487e-837d-3acb0e60dc32"),
                new CustomerName("name2", "surname2", "secondSurname2"),
                new IdDocument("44444442A"),
                new PhoneNumber("666666662"),
                new EmailAddress("user@domain2.com"),
                new CustomerAddress("address2", "city2", "00002"),
                UUID.fromString("7680ebe0-fc57-456e-9c60-f8307cf01ad7")
        );
    }
}