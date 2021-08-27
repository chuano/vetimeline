package com.vetimeline.api.domain.shared;

public class Forbidden extends Exception {
    public Forbidden(String message) {
        super(message);
    }
}
