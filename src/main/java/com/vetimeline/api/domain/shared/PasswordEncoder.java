package com.vetimeline.api.domain.shared;

import com.vetimeline.api.domain.user.Password;

public interface PasswordEncoder {
    String encode(String clearPassword);
    Boolean match(Password password, String clearPassword);
}
