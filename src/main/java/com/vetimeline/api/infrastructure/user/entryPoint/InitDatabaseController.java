package com.vetimeline.api.infrastructure.user.entryPoint;

import com.vetimeline.api.domain.user.CompleteName;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.auth.PasswordEncoder;
import com.vetimeline.api.domain.user.Password;
import com.vetimeline.api.domain.user.User;
import com.vetimeline.api.domain.user.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class InitDatabaseController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDatabaseController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "init_db", method = RequestMethod.GET)
    public String initDatabase() {
        List<User> users = userRepository.findAll(1, 1);
        if (users.size() > 0) {
            return "OK";
        }
        User user = new User(
                UUID.randomUUID(),
                new CompleteName("Chuano", "Puchol", "Marchuet"),
                new EmailAddress("chuano@gmail.com"),
                UUID.fromString("5179a505-a291-47c2-b832-39c78a7aa516"),
                new Password(passwordEncoder.encode("1234"))
            );
        userRepository.save(user);

        return "OK";
    }
}
