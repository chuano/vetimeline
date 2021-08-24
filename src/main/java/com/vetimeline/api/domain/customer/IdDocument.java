package com.vetimeline.api.domain.customer;

import javax.persistence.Column;

public class IdDocument {
    @Column(name = "id_document")
    private String value;

    private IdDocument() {
    }

    public IdDocument(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
