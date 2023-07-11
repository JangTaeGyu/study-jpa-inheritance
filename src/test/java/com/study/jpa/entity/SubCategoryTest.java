package com.study.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubCategoryTest {
    @Test
    @DisplayName("2 Depth 카테고리 Entity 만들기")
    void makeSubCategoryEntity() {
        String categoryName = "MAIN NAME";
        String categoryDescription = "MAIN DESCRIPTION";
        String subCategoryName = "SUB NAME";
        String subCategoryDescription = "SUB DESCRIPTION";
        MainCategory mainCategory = new MainCategory(categoryName, categoryDescription);
        SubCategory subCategory = new SubCategory(subCategoryName, subCategoryDescription, mainCategory);

        assertThat(CategoryType.SUB).isEqualTo(subCategory.getType());
        assertThat(subCategoryName).isEqualTo(subCategory.getName());
        assertThat(subCategoryDescription).isEqualTo(subCategory.getDescription());
        assertThat(CategoryType.MAIN).isEqualTo(subCategory.getParentCategory().getType());
        assertThat(categoryName).isEqualTo(subCategory.getParentCategory().getName());
        assertThat(categoryDescription).isEqualTo(subCategory.getParentCategory().getDescription());
    }
}