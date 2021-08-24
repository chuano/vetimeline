package com.vetimeline.api.domain.customer;

public class CustomerNotFound extends Exception {
    public CustomerNotFound() {
        super("Customer not found");
    }
}
