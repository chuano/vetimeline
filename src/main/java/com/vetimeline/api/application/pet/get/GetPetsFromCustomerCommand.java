package com.vetimeline.api.application.pet.get;

public class GetPetsFromCustomerCommand {
    private final Integer page;
    private final Integer limit;
    private final String customer;

    public GetPetsFromCustomerCommand(Integer page, Integer limit, String customer) {
        this.page = page;
        this.limit = limit;
        this.customer = customer;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getCustomer() {
        return customer;
    }
}
