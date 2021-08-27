package com.vetimeline.api.infrastructure.customer.entryPoint;

public class CreateCustomerRequest {
    public String firstName;
    public String firstSurname;
    public String secondSurname;
    public String idDocument;
    public String phone;
    public String email;
    public String address;
    public String city;
    public String zipCode;

    public CreateCustomerRequest(String firstName, String firstSurname, String secondSurname, String idDocument,
                                 String phone, String email, String address, String city, String zipCode) {
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.idDocument = idDocument;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }
}
