package com.vetimeline.api.application.pet.get;

import com.vetimeline.api.application.pet.PetDTO;
import com.vetimeline.api.domain.pet.Pet;
import com.vetimeline.api.domain.shared.ListResponse;

import java.util.List;
import java.util.stream.Collectors;

public class GetPetsResponse extends ListResponse<PetDTO> {
    public GetPetsResponse(List<Pet> data, Integer page, Integer limit) {
        super(
                data.stream()
                        .map(PetDTO::fromEntity)
                        .collect(Collectors.toList()),
                page,
                limit
        );
    }
}
