package com.vetimeline.api.domain.user;

public class UserNotFound extends Exception {
    public UserNotFound() {
        super("User not found");
    }
}
