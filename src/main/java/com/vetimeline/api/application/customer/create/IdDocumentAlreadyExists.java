package com.vetimeline.api.application.customer.create;

public class IdDocumentAlreadyExists extends Throwable {
    public IdDocumentAlreadyExists() {
        super("Id document already exists");
    }
}
