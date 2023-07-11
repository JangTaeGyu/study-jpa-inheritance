package com.study.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {
    @Test
    @DisplayName("카테고리 Entity 만들기")
    void makeCategoryEntity() {
        String categoryName = "NAME";
        String categoryDescription = "DESCRIPTION";
        Category category = new Category(categoryName, categoryDescription);

        assertThat(categoryName).isEqualTo(category.getName());
        assertThat(categoryDescription).isEqualTo(category.getDescription());
    }
}