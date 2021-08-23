package com.vetimeline.api.domain.shared;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailAddress {
    @Column(name = "email")
    private String value;

    private EmailAddress() {
    }

    public EmailAddress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
