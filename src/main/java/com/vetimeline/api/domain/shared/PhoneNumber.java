package com.vetimeline.api.domain.shared;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {
    @Column(name = "phone")
    private String value;

    private PhoneNumber() {
    }

    public PhoneNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
