package com.study.jpa.service;

import com.study.jpa.entity.Category;
import com.study.jpa.entity.CategoryType;
import com.study.jpa.entity.SubCategory;
import com.study.jpa.entity.SubSubCategory;
import com.study.jpa.exception.CategoryNotFoundException;
import com.study.jpa.service.request.CreateCategoryRequest;
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
class CreateCategoryServiceTest {
    @Autowired
    private CreateCategoryService createCategoryService;

    @Test
    @DisplayName("메인 카테고리 등록한다.")
    void createMainCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest("MAIN NAME", "MAIN DESCRIPTION");
        Category category = createCategoryService.createCategory(request);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getType()).isEqualTo(CategoryType.MAIN);
    }

    @Test
    @DisplayName("카테고리 등록 할 때 부모 카테고리 존재하지 않으면 오류가 발생한다.")
    void notFoundParentCategory() {
        assertThrows(CategoryNotFoundException.class, () -> {
            CreateCategoryRequest request = new CreateCategoryRequest("NAME", "DESCRIPTION", 0L);
            createCategoryService.createCategory(request);
        });
    }

    @Test
    @DisplayName("2 Depth 카테고리 등록한다.")
    void createSubCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest("SUB NAME", "SUB DESCRIPTION", 1L);
        SubCategory category = (SubCategory) createCategoryService.createCategory(request);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getType()).isEqualTo(CategoryType.SUB);
        assertThat(category.getParentCategory().getId()).isNotNull();
        assertThat(category.getParentCategory().getType()).isEqualTo(CategoryType.MAIN);
    }

    @Test
    @DisplayName("3 Depth 카테고리 등록한다.")
    void createSubSubCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest("SUB SUB NAME", "SUB SUB DESCRIPTION", 2L);
        SubSubCategory category = (SubSubCategory) createCategoryService.createCategory(request);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getType()).isEqualTo(CategoryType.SUBSUB);
        assertThat(category.getParentCategory().getId()).isNotNull();
        assertThat(category.getParentCategory().getType()).isEqualTo(CategoryType.SUB);
        assertThat(category.getParentCategory().getParentCategory().getId()).isNotNull();
        assertThat(category.getParentCategory().getParentCategory().getType()).isEqualTo(CategoryType.MAIN);
    }

    @Test
    @DisplayName("3 Depth 하위 카테고리 등록을 제한한다.")
    void limitedCategoryCreateDepth() {
        assertThrows(RuntimeException.class, () -> {
            CreateCategoryRequest request = new CreateCategoryRequest("SUB SUB SUB NAME", "SUB SUB SUB DESCRIPTION", 3L);
            createCategoryService.createCategory(request);
        });
    }
}