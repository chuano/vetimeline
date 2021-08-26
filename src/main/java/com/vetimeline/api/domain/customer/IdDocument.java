package com.vetimeline.api.domain.customer;

import javax.persistence.Column;
import java.util.ArrayList;

public class IdDocument {
    @Column(name = "id_document")
    private String value;

    private IdDocument() {
    }

    public IdDocument(String value) {
        value = value.replace(" ", "");
        value = value.replace("-", "");

        if (value.length() != 9) {
            this.value = value;
        } else if (Character.isDigit(value.charAt(0)) && Character.isLetter(value.charAt(8))){
            this.value = value.substring(0, 8) + "-" + value.charAt(8);
        } else if (Character.isLetter(value.charAt(0)) && Character.isDigit(value.charAt(8))){
            this.value = value.charAt(0) + "-" + value.substring(1, 9);
        } else if (Character.isLetter(value.charAt(0)) && Character.isLetter(value.charAt(8))){
            this.value = value.charAt(0) + "-" + value.substring(1, 8) + "-" + value.charAt(8);
        } else {
            this.value = value;
        }
    }

    public String getValue() {
        return value;
    }
}
