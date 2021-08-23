package com.vetimeline.api.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Password {
    @Column(name = "password")
    private String value;

    private Password() {
    }

    public Password(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
