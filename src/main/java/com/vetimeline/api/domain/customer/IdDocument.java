package com.vetimeline.api.domain.customer;

import com.vetimeline.api.domain.customer.idDocumentFormatter.IdDocumentFormatter;
import com.vetimeline.api.domain.customer.idDocumentFormatter.IdDocumentFormatterFactory;

import javax.persistence.Column;

public class IdDocument {
    @Column(name = "id_document")
    private String value;

    private IdDocument() {
    }

    public IdDocument(String value) {
        value = this.cleanNumber(value);
        IdDocumentFormatter formatter = new IdDocumentFormatterFactory().getFormatter(value);
        this.value = formatter.format(value);
    }

    public String getValue() {
        return value;
    }

    private String cleanNumber(String idDocumentNumber) {
        return idDocumentNumber
                .replace(" ", "")
                .replace("-", "");
    }
}
