package com.study.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainCategoryTest {
    @Test
    @DisplayName("메인 카테고리 Entity 만들기")
    void makeMainCategoryEntity() {
        String categoryName = "MAIN NAME";
        String categoryDescription = "MAIN DESCRIPTION";
        MainCategory mainCategory = new MainCategory(categoryName, categoryDescription);

        assertThat(CategoryType.MAIN).isEqualTo(mainCategory.getType());
        assertThat(categoryName).isEqualTo(mainCategory.getName());
        assertThat(categoryDescription).isEqualTo(mainCategory.getDescription());
    }
}