package com.vetimeline.api.domain.customer;

import com.sun.istack.NotNull;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.PhoneNumber;
import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    @Embedded
    private CustomerName name;

    @NotNull
    @Embedded
    private IdDocument idDocument;

    @Embedded
    private PhoneNumber phone;

    @Embedded
    private EmailAddress email;

    @Embedded
    private CustomerAddress address;

    private CustomerStatus status;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID organization;

    protected Customer() {
    }

    public Customer(UUID id, CustomerName name, IdDocument idDocument, PhoneNumber phone, EmailAddress email,
                    CustomerAddress address, CustomerStatus status, UUID organization) {
        this.id = id;
        this.name = name;
        this.idDocument = idDocument;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
        this.organization = organization;
    }

    public void update(CustomerName name, IdDocument idDocument, PhoneNumber phone, EmailAddress email,
                       CustomerAddress address) {
        this.name = name;
        this.idDocument = idDocument;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public void updateStatus(CustomerStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public CustomerName getName() {
        return name;
    }

    public IdDocument getIdDocument() {
        return idDocument;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public CustomerAddress getAddress() {
        return address;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public UUID getOrganization() {
        return organization;
    }
}
