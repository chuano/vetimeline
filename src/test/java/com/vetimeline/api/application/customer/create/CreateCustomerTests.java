package com.vetimeline.api.application.customer.create;

import com.vetimeline.api.domain.customer.*;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CreateCustomerTests {
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
    }

    @Test
    public void ShouldCreateCustomer() {
        CreateCustomerCommand command = getCreateCustomerCommand();
        CreateCustomerHandler handler = new CreateCustomerHandler(customerRepository);
        CreateCustomerResponse response = handler.execute(command);

        assertTrue(new ReflectionEquals(getExpected()).matches(response));
        verify(customerRepository).save(any(Customer.class));
    }

    private CreateCustomerCommand getCreateCustomerCommand() {
        return new CreateCustomerCommand(
                "1499aab2-be02-487e-837d-3acb0e60dc32",
                "name",
                "surname",
                "secondSurname",
                "44444444A",
                "666666666",
                "user@domain.com",
                "address",
                "city",
                "00000",
                "7680ebe0-fc57-456e-9c60-f8307cf01ad7"
        );
    }

    private CreateCustomerResponse getExpected() {
        return new CreateCustomerResponse(createCustomer());
    }

    private Customer createCustomer() {
        return new Customer(
                UUID.fromString("1499aab2-be02-487e-837d-3acb0e60dc32"),
                new CustomerName("name", "surname", "secondSurname"),
                new IdDocument("44444444A"),
                new PhoneNumber("666666666"),
                new EmailAddress("user@domain.com"),
                new CustomerAddress("address", "city", "00000"),
                UUID.fromString("7680ebe0-fc57-456e-9c60-f8307cf01ad7")
        );
    }
}
