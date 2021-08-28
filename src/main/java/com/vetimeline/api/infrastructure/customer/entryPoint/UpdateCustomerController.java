package com.vetimeline.api.infrastructure.customer.entryPoint;

import com.vetimeline.api.application.customer.update.UpdateCustomerCommand;
import com.vetimeline.api.application.customer.update.UpdateCustomerHandler;
import com.vetimeline.api.application.customer.update.UpdateCustomerResponse;
import com.vetimeline.api.domain.customer.CustomerNotFound;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.customer.IdDocumentAlreadyExists;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;
import com.vetimeline.api.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateCustomerController {
    private CustomerRepository customerRepository;

    public UpdateCustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/v1/customers/{id}", method = RequestMethod.PUT)
    public UpdateCustomerResponse update(
            @PathVariable String id,
            @RequestBody() UpdateCustomerRequest request,
            Authentication authentication
    ) throws IdDocumentAlreadyExists, CustomerNotFound, EntityNotFound, Forbidden {
        User user = (User) authentication.getCredentials();
        UpdateCustomerCommand command = new UpdateCustomerCommand(
                id,
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
        UpdateCustomerHandler handler = new UpdateCustomerHandler(customerRepository);
        return handler.execute(command);
    }
}
