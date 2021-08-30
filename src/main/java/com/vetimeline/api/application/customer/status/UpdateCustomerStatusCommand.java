package com.vetimeline.api.application.customer.status;

public class UpdateCustomerStatusCommand {
    private final String id;
    private final String status;
    private final String organization;

    public UpdateCustomerStatusCommand(String id, String status, String organization) {
        this.id = id;
        this.status = status;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getOrganization() {
        return organization;
    }
}
