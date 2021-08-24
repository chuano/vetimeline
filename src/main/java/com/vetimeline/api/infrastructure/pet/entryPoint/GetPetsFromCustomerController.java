package com.vetimeline.api.infrastructure.pet.entryPoint;

import com.vetimeline.api.application.pet.get.GetPetsFromCustomerCommand;
import com.vetimeline.api.application.pet.get.GetPetsFromCustomerHandler;
import com.vetimeline.api.application.pet.get.GetPetsResponse;
import com.vetimeline.api.domain.pet.PetRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPetsFromCustomerController {
    private final PetRepository petRepository;

    public GetPetsFromCustomerController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @RequestMapping(value = "/v1/customers/{customer}/pets")
    public GetPetsResponse getPets(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @PathVariable String customer
    ) {
        GetPetsFromCustomerCommand command = new GetPetsFromCustomerCommand(page, limit, customer);
        GetPetsFromCustomerHandler handler = new GetPetsFromCustomerHandler(petRepository);
        return handler.execute(command);
    }
}
