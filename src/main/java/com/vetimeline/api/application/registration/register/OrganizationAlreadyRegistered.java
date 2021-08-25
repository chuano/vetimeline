package com.vetimeline.api.application.registration.register;

public class OrganizationAlreadyRegistered extends Throwable {
    public OrganizationAlreadyRegistered() {
        super("Organization already registered");
    }
}
