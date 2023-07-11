package com.study.jpa.service.request;

import lombok.Getter;

@Getter
public class CreateCategoryRequest {
    private String name;
    private String description;
    private Long parentId;

    public CreateCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CreateCategoryRequest(String name, String description, Long parentId) {
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }
}
