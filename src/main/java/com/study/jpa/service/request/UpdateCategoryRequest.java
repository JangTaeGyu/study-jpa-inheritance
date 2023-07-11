package com.study.jpa.service.request;

import lombok.Getter;

@Getter
public class UpdateCategoryRequest {
    private String name;
    private String description;

    public UpdateCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
