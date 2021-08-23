package com.vetimeline.api.infrastructure.shared;

import com.vetimeline.api.domain.shared.PasswordEncoder;
import com.vetimeline.api.domain.user.Password;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(String clearPassword) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        return argon2.hash(3, 1024, 3, clearPassword);
    }

    @Override
    public Boolean verify(Password password, String clearPassword) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        return argon2.verify(password.getValue(), clearPassword);
    }
}
