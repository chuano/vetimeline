package com.vetimeline.api.application.user.getUsers;

public class GetUsersCommand {
    private Integer page;
    private Integer limit;

    public GetUsersCommand(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getLimit() {
        return limit;
    }
}
