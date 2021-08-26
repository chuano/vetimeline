package com.vetimeline.api.domain.customer.idDocumentFormatter;

public class DniFormatter implements IdDocumentFormatter {
    @Override
    public String format(String idDocumentNumber) {
        return idDocumentNumber.substring(0, 8) + "-" + idDocumentNumber.charAt(8);
    }
}
