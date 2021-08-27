package com.vetimeline.api.domain.customer;

public class IdDocumentAlreadyExists extends Throwable {
    public IdDocumentAlreadyExists() {
        super("Id document already exists");
    }
}
