package com.vetimeline.api.application.registration.register;

public class EmailAlreadyInUse extends Throwable {
    public EmailAlreadyInUse() {
        super("Email already in use");
    }
}
