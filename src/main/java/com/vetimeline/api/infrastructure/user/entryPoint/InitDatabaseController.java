package com.vetimeline.api.infrastructure.user.entryPoint;

import com.vetimeline.api.domain.auth.PasswordEncoder;
import com.vetimeline.api.domain.customer.*;
import com.vetimeline.api.domain.organization.Organization;
import com.vetimeline.api.domain.organization.OrganizationName;
import com.vetimeline.api.domain.organization.OrganizationRepository;
import com.vetimeline.api.domain.pet.Pet;
import com.vetimeline.api.domain.pet.PetName;
import com.vetimeline.api.domain.pet.PetRepository;
import com.vetimeline.api.domain.shared.EmailAddress;
import com.vetimeline.api.domain.shared.PhoneNumber;
import com.vetimeline.api.domain.user.CompleteName;
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
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDatabaseController(OrganizationRepository organizationRepository, UserRepository userRepository,
                                  CustomerRepository customerRepository, PetRepository petRepository,
                                  PasswordEncoder passwordEncoder) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "init_db", method = RequestMethod.GET)
    public String initDatabase() {
        List<Organization> organizations = organizationRepository.findAll(1, 1);
        if (organizations.size() > 0) {
            return "ALREADY OK";
        }

        Organization organization = new Organization(UUID.randomUUID(), new OrganizationName("Testerinario"));
        organizationRepository.save(organization);

        User user = new User(
                UUID.randomUUID(),
                new CompleteName("Chuano", "Puchol", "Marchuet"),
                new EmailAddress("chuano@gmail.com"),
                organization.getId(),
                new Password(passwordEncoder.encode("1234"))
        );
        userRepository.save(user);

        Customer customer = new Customer(
                UUID.randomUUID(),
                new CustomerName("Núria", "Camarasa", "Valiente"),
                new IdDocument("778998876A"),
                new PhoneNumber("666666666"),
                new EmailAddress("user@domain.com"),
                new CustomerAddress("Xàbia, 1", "Monóvar", "03640"),
                CustomerStatus.ACTIVE,
                organization.getId()
        );
        customerRepository.save(customer);

        Pet pet = new Pet(UUID.randomUUID(), new PetName("Mao"), customer.getId());
        petRepository.save(pet);
        pet = new Pet(UUID.randomUUID(), new PetName("Nita"), customer.getId());
        petRepository.save(pet);
        pet = new Pet(UUID.randomUUID(), new PetName("Número 3"), customer.getId());
        petRepository.save(pet);

        return "OK";
    }
}
