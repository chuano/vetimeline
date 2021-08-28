package com.vetimeline.api.application.customer.status;

public class ActivateCustomerCommand {
    private String id;
    private String organization;

    public ActivateCustomerCommand(String id, String organization) {
        this.id = id;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public String getOrganization() {
        return organization;
    }
}
