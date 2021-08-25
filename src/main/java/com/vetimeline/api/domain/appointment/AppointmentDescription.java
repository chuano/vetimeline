package com.vetimeline.api.domain.appointment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppointmentDescription {
    @Column(name = "description")
    private String value;

    public AppointmentDescription() {}

    public AppointmentDescription(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
