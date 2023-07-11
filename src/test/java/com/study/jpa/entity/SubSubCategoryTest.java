package com.study.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubSubCategoryTest {
    @Test
    @DisplayName("3 Depth 카테고리 Entity 만들기")
    void makeSubSubCategoryEntity() {
        String categoryName = "MAIN NAME";
        String categoryDescription = "MAIN DESCRIPTION";
        String subCategoryName = "SUB NAME";
        String subCategoryDescription = "SUB DESCRIPTION";
        String subSubCategoryName = "SUB SUB NAME";
        String subSubCategoryDescription = "SUB SUB DESCRIPTION";
        MainCategory mainCategory = new MainCategory(categoryName, categoryDescription);
        SubCategory subCategory = new SubCategory(subCategoryName, subCategoryDescription, mainCategory);
        SubSubCategory subSubCategory = new SubSubCategory(subSubCategoryName, subSubCategoryDescription, subCategory);

        assertThat(CategoryType.SUBSUB).isEqualTo(subSubCategory.getType());
        assertThat(subSubCategoryName).isEqualTo(subSubCategory.getName());
        assertThat(subSubCategoryDescription).isEqualTo(subSubCategory.getDescription());
        assertThat(CategoryType.SUB).isEqualTo(subSubCategory.getParentCategory().getType());
        assertThat(subCategoryName).isEqualTo(subSubCategory.getParentCategory().getName());
        assertThat(subCategoryDescription).isEqualTo(subSubCategory.getParentCategory().getDescription());
        assertThat(CategoryType.MAIN).isEqualTo(subSubCategory.getParentCategory().getParentCategory().getType());
        assertThat(categoryName).isEqualTo(subSubCategory.getParentCategory().getParentCategory().getName());
        assertThat(categoryDescription).isEqualTo(subSubCategory.getParentCategory().getParentCategory().getDescription());
    }
}