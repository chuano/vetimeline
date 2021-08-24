package com.vetimeline.api.domain.customer;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerName {
    private String firstName;
    private String firstSurname;
    private String secondSurname;

    private CustomerName() {
    }

    public CustomerName(String firstSurname) {
        this.firstName = null;
        this.firstSurname = firstSurname;
        this.secondSurname = null;
    }

    public CustomerName(String firstName, String firstSurname, String secondSurname) {
        this.firstName = firstName;
        this.firstSurname = firstSurname;
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
