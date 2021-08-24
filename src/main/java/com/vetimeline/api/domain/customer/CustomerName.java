package com.vetimeline.api.domain.customer;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CustomerName {
    private String firstName;
    private String firstSurname;
    private String secondSurname;

    private CustomerName() {
    }

    public CustomerName(String firstName, String firstSurname, String secondSurname) {
        this.firstName = firstName;
        this.firstSurname = Objects.requireNonNull(firstSurname, "First surname cannot be null");
        this.secondSurname = secondSurname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }
}
