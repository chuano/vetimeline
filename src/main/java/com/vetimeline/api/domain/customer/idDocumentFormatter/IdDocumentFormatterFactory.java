package com.vetimeline.api.domain.customer.idDocumentFormatter;

public class IdDocumentFormatterFactory {
    public IdDocumentFormatter getFormatter(String idDocumentNumber) {
        if (idDocumentNumber.length() != 9) {
            return new UnknownFormatter();
        }

        if (isDni(idDocumentNumber)) {
            return new DniFormatter();
        } else if (isCif(idDocumentNumber)) {
            return new CifFormatter();
        } else if (isNie(idDocumentNumber)) {
            return new NieFormatter();
        }

        return new UnknownFormatter();
    }

    private boolean isDni(String idDocumentNumber) {
        return Character.isDigit(idDocumentNumber.charAt(0)) && Character.isLetter(idDocumentNumber.charAt(8));
    }

    private boolean isCif(String idDocumentNumber) {
        return Character.isLetter(idDocumentNumber.charAt(0)) && Character.isDigit(idDocumentNumber.charAt(8));
    }

    private boolean isNie(String idDocumentNumber) {
        return Character.isLetter(idDocumentNumber.charAt(0)) && Character.isLetter(idDocumentNumber.charAt(8));
    }
}
