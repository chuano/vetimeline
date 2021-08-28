package com.vetimeline.api.infrastructure.customer.entryPoint;

import com.vetimeline.api.application.customer.status.ActivateCustomerCommand;
import com.vetimeline.api.application.customer.status.ActivateCustomerHandler;
import com.vetimeline.api.application.customer.status.ActivateCustomerResponse;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;
import com.vetimeline.api.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivateCustomerController {
    private final CustomerRepository customerRepository;

    public ActivateCustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/v1/customers/{id}/status", method = RequestMethod.PUT)
    public ActivateCustomerResponse activate(
            @PathVariable String id,
            Authentication authentication
    ) throws EntityNotFound, Forbidden {
        User user = (User) authentication.getCredentials();
        ActivateCustomerCommand command = new ActivateCustomerCommand(id, user.getOrganization().toString());
        ActivateCustomerHandler handler = new ActivateCustomerHandler(customerRepository);
        return handler.execute(command);
    }
}
