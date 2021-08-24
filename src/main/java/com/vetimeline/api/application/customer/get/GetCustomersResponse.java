package com.vetimeline.api.application.customer.get;

import com.vetimeline.api.application.customer.CustomerDTO;
import com.vetimeline.api.domain.customer.Customer;
import com.vetimeline.api.domain.shared.ListResponse;

import java.util.List;
import java.util.stream.Collectors;

public class GetCustomersResponse extends ListResponse<CustomerDTO> {
    public GetCustomersResponse(List<Customer> data, Integer page, Integer limit) {
        super(data.stream().map(CustomerDTO::fromEntity).collect(Collectors.toList()), page, limit);
    }
}
