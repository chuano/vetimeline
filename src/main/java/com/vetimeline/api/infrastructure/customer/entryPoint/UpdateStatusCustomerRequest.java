package com.vetimeline.api.infrastructure.customer.entryPoint;

import com.vetimeline.api.domain.customer.CustomerStatus;

public class UpdateStatusCustomerRequest {
    public final String status;

    public UpdateStatusCustomerRequest(String status) {
        CustomerStatus.valueOf(status);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
