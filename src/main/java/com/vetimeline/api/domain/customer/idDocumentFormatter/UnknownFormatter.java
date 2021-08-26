package com.vetimeline.api.domain.customer.idDocumentFormatter;

public class UnknownFormatter implements IdDocumentFormatter {
    @Override
    public String format(String idDocumentNumber) {
        return idDocumentNumber;
    }
}
