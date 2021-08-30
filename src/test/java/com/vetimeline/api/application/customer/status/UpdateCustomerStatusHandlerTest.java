package com.vetimeline.api.application.customer.status;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class UpdateCustomerStatusHandlerTest {
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
    }

    @Test
    public void ShouldActivateCustomer() throws EntityNotFound, Forbidden {
        when(customerRepository.find(any()))
                .thenReturn(getCustomer("7680ebe0-fc57-456e-9c60-f8307cf01ad7", CustomerStatus.INACTIVE));
        when(customerRepository.findBy(any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        UpdateCustomerStatusCommand command = getUpdateCustomerStatusCommand("ACTIVE");
        UpdatCustomerStatusHandler handler = new UpdatCustomerStatusHandler(customerRepository);
        UpdateCustomerStatusResponse response = handler.execute(command);

        assertTrue(new ReflectionEquals(getExpected(CustomerStatus.ACTIVE)).matches(response));
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void ShouldDeactivateCustomer() throws EntityNotFound, Forbidden {
        when(customerRepository.find(any()))
                .thenReturn(getCustomer("7680ebe0-fc57-456e-9c60-f8307cf01ad7", CustomerStatus.ACTIVE));

        UpdateCustomerStatusCommand command = getUpdateCustomerStatusCommand("INACTIVE");
        UpdatCustomerStatusHandler handler = new UpdatCustomerStatusHandler(customerRepository);
        UpdateCustomerStatusResponse response = handler.execute(command);

        assertTrue(new ReflectionEquals(getExpected(CustomerStatus.INACTIVE)).matches(response));
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void ShouldThrowForbiddenExceptionExists() throws EntityNotFound {
        when(customerRepository.find(any()))
                .thenReturn(getCustomer("00000000-0000-0000-0000-000000000000", CustomerStatus.ACTIVE));

        UpdateCustomerStatusCommand command = getUpdateCustomerStatusCommand("INACTIVE");
        UpdatCustomerStatusHandler handler = new UpdatCustomerStatusHandler(customerRepository);

        assertThrows(Forbidden.class, () -> handler.execute(command));
    }

    @Test
    public void ShouldThrowIllegalArgumentExceptionByInvalidStatus() throws EntityNotFound {
        when(customerRepository.find(any()))
                .thenReturn(getCustomer("7680ebe0-fc57-456e-9c60-f8307cf01ad7", CustomerStatus.ACTIVE));

        UpdateCustomerStatusCommand command = getUpdateCustomerStatusCommand("UNKNOWN");
        UpdatCustomerStatusHandler handler = new UpdatCustomerStatusHandler(customerRepository);

        assertThrows(IllegalArgumentException.class, () -> handler.execute(command));
    }

    private UpdateCustomerStatusCommand getUpdateCustomerStatusCommand(String status) {
        return new UpdateCustomerStatusCommand(
                "1499aab2-be02-487e-837d-3acb0e60dc32",
                status,
                "7680ebe0-fc57-456e-9c60-f8307cf01ad7"
        );
    }

    private Customer getCustomer(String organization, CustomerStatus status) {
        return new Customer(
                UUID.fromString("1499aab2-be02-487e-837d-3acb0e60dc32"),
                new CustomerName("name", "surname", "secondSurname"),
                new IdDocument("44444444A"),
                new PhoneNumber("666666666"),
                new EmailAddress("user@domain.com"),
                new CustomerAddress("address", "city", "00000"),
                status,
                UUID.fromString(organization)
        );
    }

    private UpdateCustomerStatusResponse getExpected(CustomerStatus status) {
        return new UpdateCustomerStatusResponse(
                getCustomer("7680ebe0-fc57-456e-9c60-f8307cf01ad7", status)
        );
    }
}