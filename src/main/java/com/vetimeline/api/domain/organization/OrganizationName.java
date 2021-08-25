package com.vetimeline.api.domain.organization;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrganizationName {
    @Column(name = "name")
    private String value;

    public OrganizationName() {}

    public OrganizationName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
