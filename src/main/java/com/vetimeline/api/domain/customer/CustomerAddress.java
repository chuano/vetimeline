package com.vetimeline.api.domain.customer;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerAddress {
    private String address;
    private String city;
    private String zipCode;

    private CustomerAddress() {
    }

    public CustomerAddress(String address, String city, String zipCode) {
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }
}
