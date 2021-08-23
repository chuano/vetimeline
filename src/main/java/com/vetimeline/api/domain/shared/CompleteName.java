package com.vetimeline.api.domain.shared;

import javax.persistence.Embeddable;

@Embeddable
public class CompleteName {
    private String firstName;
    private String firstSurname;
    private String secondSurname;

    private CompleteName() {
    }

    public CompleteName(String firstName, String firstSurname, String secondSurname) {
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

    @Override
    public String toString() {
        return (firstName + " " + firstSurname + " " + secondSurname).trim();
    }
}
