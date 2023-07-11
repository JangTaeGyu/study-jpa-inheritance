package com.study.jpa.service;

import com.study.jpa.entity.Category;
import com.study.jpa.entity.CategoryType;
import com.study.jpa.entity.SubCategory;
import com.study.jpa.entity.SubSubCategory;
import com.study.jpa.exception.CategoryNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/category-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CategoryQueryServiceTest {
    @Autowired private CategoryQueryService categoryQueryService;

    @Test
    @DisplayName("존재하지 않는 카테고리 조회하면 오류가 발생 한다.")
    void notFoundCategory() {
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryQueryService.getCategory(0L);
        });
    }

    @Test
    @DisplayName("메인 카테고리 조회를 한다.")
    void getMainCategory() {
        Category category = categoryQueryService.getCategory(1L);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getType()).isEqualTo(CategoryType.MAIN);
    }

    @Test
    @DisplayName("2 Depth 카테고리 조회를 한다.")
    void getSubCategory() {
        SubCategory category = (SubCategory) categoryQueryService.getCategory(2L);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getType()).isEqualTo(CategoryType.SUB);
        assertThat(category.getParentCategory().getId()).isNotNull();
        assertThat(category.getParentCategory().getType()).isEqualTo(CategoryType.MAIN);
    }

    @Test
    @DisplayName("3 Depth 카테고리 조회를 한다.")
    void getSubSubCategory() {
        SubSubCategory category = (SubSubCategory) categoryQueryService.getCategory(3L);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getType()).isEqualTo(CategoryType.SUBSUB);
        assertThat(category.getParentCategory().getId()).isNotNull();
        assertThat(category.getParentCategory().getType()).isEqualTo(CategoryType.SUB);
        assertThat(category.getParentCategory().getParentCategory().getId()).isNotNull();
        assertThat(category.getParentCategory().getParentCategory().getType()).isEqualTo(CategoryType.MAIN);
    }
}