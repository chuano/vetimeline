package com.vetimeline.api.infrastructure.customer.entryPoint;

import com.vetimeline.api.application.customer.get.GetCustomersCommand;
import com.vetimeline.api.application.customer.get.GetCustomersHandler;
import com.vetimeline.api.application.customer.get.GetCustomersResponse;
import com.vetimeline.api.domain.customer.CustomerRepository;
import com.vetimeline.api.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCustomersController {
    private final CustomerRepository customerRepository;

    public GetCustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/v1/customers", method = RequestMethod.GET)
    public GetCustomersResponse getCustomers(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            Authentication authentication
    ) {
        User user = (User) authentication.getCredentials();
        GetCustomersCommand command = new GetCustomersCommand(page, limit, user.getOrganization().toString());
        GetCustomersHandler handler = new GetCustomersHandler(customerRepository);
        return handler.execute(command);
    }
}
