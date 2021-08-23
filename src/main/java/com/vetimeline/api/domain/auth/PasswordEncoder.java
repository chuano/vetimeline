package com.vetimeline.api.domain.auth;

import com.vetimeline.api.domain.user.Password;

public interface PasswordEncoder {
    String encode(String clearPassword);
    Boolean verify(Password password, String clearPassword);
}
