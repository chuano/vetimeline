package com.vetimeline.api.application.user.getUsers;

public class GetUsersCommand {
    private final Integer page;
    private final Integer limit;
    private final String organization;

    public GetUsersCommand(Integer page, Integer limit, String organization) {
        this.page = page;
        this.limit = limit;
        this.organization = organization;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getOrganization() {
        return organization;
    }
}
