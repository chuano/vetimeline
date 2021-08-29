package com.vetimeline.api.application.customer.status;

public class DeactivateCustomerCommand {
    private final String id;
    private final String organization;

    public DeactivateCustomerCommand(String id, String organization) {
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
