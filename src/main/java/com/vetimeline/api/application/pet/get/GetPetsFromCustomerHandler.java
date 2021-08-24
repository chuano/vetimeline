package com.vetimeline.api.application.pet.get;

import com.vetimeline.api.domain.pet.Pet;
import com.vetimeline.api.domain.pet.PetRepository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GetPetsFromCustomerHandler {
    private final PetRepository petRepository;

    public GetPetsFromCustomerHandler(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public GetPetsResponse execute(GetPetsFromCustomerCommand command) {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("customer", UUID.fromString(command.getCustomer()));
        List<Pet> pets = petRepository.findBy(criteria, command.getPage(), command.getLimit());

        return new GetPetsResponse(pets, command.getPage(), command.getLimit());
    }
}
