package com.vetimeline.api.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Password {
    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

   public Boolean match(String password) {
        return Objects.equals(value, password);
   }
}
