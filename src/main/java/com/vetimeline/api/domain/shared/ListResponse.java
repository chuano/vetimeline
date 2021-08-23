package com.vetimeline.api.domain.shared;

import java.util.List;

public class ListResponse<T> {
    private List<T> data;
    private Integer page;
    private Integer limit;

    public ListResponse(List<T> data, Integer page, Integer limit) {
        this.data = data;
        this.page = page;
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getLimit() {
        return limit;
    }

    public List<T> getData() {
        return data;
    }
}
