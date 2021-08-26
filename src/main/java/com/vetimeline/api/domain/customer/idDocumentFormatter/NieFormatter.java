package com.vetimeline.api.domain.customer.idDocumentFormatter;

public class NieFormatter implements IdDocumentFormatter {
    @Override
    public String format(String idDocumentNumber) {
        return idDocumentNumber.charAt(0) + "-" + idDocumentNumber.substring(1, 8) + "-" + idDocumentNumber.charAt(8);
    }
}
