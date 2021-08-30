package com.vetimeline.api.infrastructure.customer.entryPoint;

import com.vetimeline.api.application.customer.status.UpdatCustomerStatusHandler;
import com.vetimeline.api.application.customer.status.UpdateCustomerStatusCommand;
import com.vetimeline.api.application.customer.status.UpdateCustomerStatusResponse;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.shared.EntityNotFound;
import com.vetimeline.api.domain.shared.Forbidden;
import com.vetimeline.api.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateStatusCustomerController {
    private final CustomerRepository customerRepository;

    public UpdateStatusCustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/v1/customers/{id}/status", method = RequestMethod.PUT)
    public UpdateCustomerStatusResponse activate(
            @PathVariable String id,
            @RequestBody() UpdateStatusCustomerRequest request,
            Authentication authentication
    ) throws EntityNotFound, Forbidden {
        User user = (User) authentication.getCredentials();
        UpdateCustomerStatusCommand command = new UpdateCustomerStatusCommand(
                id,
                request.status,
                user.getOrganization().toString()
        );
        UpdatCustomerStatusHandler handler = new UpdatCustomerStatusHandler(customerRepository);
        return handler.execute(command);
    }
}
