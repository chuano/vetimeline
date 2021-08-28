package com.vetimeline.api.application.customer;

import com.vetimeline.api.domain.customer.Customer;

public class CustomerDTO {
    private final String id;
    private final String firstName;
    private final String firstSurname;
    private final String secondSurname;
    private final String idDocument;
    private final String phone;
    private final String address;
    private final String city;
    private final String zipCode;
    private final String status;

    public CustomerDTO(String id, String firstName, String firstSurname, String secondSurname, String idDocument,
                       String phone, String address, String city, String zipCode, String status) {
        this.id = id;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.idDocument = idDocument;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.status = status;
    }

    public static CustomerDTO fromEntity(Customer customer) {
        return new CustomerDTO(
                customer.getId().toString(),
                customer.getName().getFirstName(),
                customer.getName().getFirstSurname(),
                customer.getName().getSecondSurname(),
                null != customer.getIdDocument() ? customer.getIdDocument().getValue() : null,
                null != customer.getPhone() ? customer.getPhone().getValue() : null,
                null != customer.getAddress() ? customer.getAddress().getAddress() : null,
                null != customer.getAddress() ? customer.getAddress().getCity() : null,
                null != customer.getAddress() ? customer.getAddress().getZipCode() : null,
                customer.getStatus().toString()
        );
    }

    public String getId() {
        return id;
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

    public String getIdDocument() {
        return idDocument;
    }

    public String getPhone() {
        return phone;
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

    public String getStatus() {
        return status;
    }
}
