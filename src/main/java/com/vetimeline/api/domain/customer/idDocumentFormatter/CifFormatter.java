package com.vetimeline.api.domain.customer.idDocumentFormatter;

public class CifFormatter implements IdDocumentFormatter {
    @Override
    public String format(String idDocumentNumber) {
        return idDocumentNumber.charAt(0) + "-" + idDocumentNumber.substring(1, 9);
    }
}
