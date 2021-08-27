package com.vetimeline.api.infrastructure.customer.entryPoint;

import com.vetimeline.api.application.customer.create.CreateCustomerCommand;
import com.vetimeline.api.application.customer.create.CreateCustomerHandler;
import com.vetimeline.api.application.customer.create.CreateCustomerResponse;
import com.vetimeline.api.application.customer.create.IdDocumentAlreadyExists;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CreateCustomerController {
    private final CustomerRepository customerRepository;

    public CreateCustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/v1/customers", method = RequestMethod.POST)
    public CreateCustomerResponse getCustomer(
            @RequestBody() CreateCustomerRequest request,
            Authentication authentication
    ) throws IdDocumentAlreadyExists {
        User user = (User) authentication.getCredentials();

        CreateCustomerCommand command = new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                request.firstName,
                request.firstSurname,
                request.secondSurname,
                request.idDocument,
                request.phone,
                request.email,
                request.address,
                request.city,
                request.zipCode,
                user.getOrganization().toString()
        );
        CreateCustomerHandler handler = new CreateCustomerHandler(customerRepository);
        return handler.execute(command);
    }
}
