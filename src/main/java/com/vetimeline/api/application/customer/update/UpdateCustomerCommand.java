package com.vetimeline.api.application.customer.update;

public class UpdateCustomerCommand {
    private final String id;
    private final String firstName;
    private final String firstSurname;
    private final String secondSurname;
    private final String idDocument;
    private final String phone;
    private final String email;
    private final String address;
    private final String city;
    private final String zipCode;
    private final String organization;

    public UpdateCustomerCommand(String id, String firstName, String firstSurname, String secondSurname,
                                 String idDocument, String phone, String email, String address, String city,
                                 String zipCode, String organization) {
        this.id = id;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.idDocument = idDocument;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.organization = organization;
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

    public String getEmail() {
        return email;
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

    public String getOrganization() {
        return organization;
    }
}
