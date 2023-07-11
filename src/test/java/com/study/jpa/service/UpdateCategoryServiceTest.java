package com.study.jpa.service;

import com.study.jpa.entity.Category;
import com.study.jpa.exception.CategoryNotFoundException;
import com.study.jpa.service.request.UpdateCategoryRequest;
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
class UpdateCategoryServiceTest {
    @Autowired private UpdateCategoryService updateCategoryService;

    @Test
    @DisplayName("존재하지 않는 카테고리 수정할 때 오류가 발생한다.")
    void notFoundCategory() {
        assertThrows(CategoryNotFoundException.class, () -> {
            UpdateCategoryRequest request = new UpdateCategoryRequest("UPDATE NAME", "UPDATE DESCRIPTION");
            updateCategoryService.updateCategory(0L, request);
        });
    }

    @Test
    @DisplayName("카테고리 수정한다.")
    void updateCategory() {
        Long categoryId = 1L;
        UpdateCategoryRequest request = new UpdateCategoryRequest("UPDATE NAME", "UPDATE DESCRIPTION");

        Category category = updateCategoryService.updateCategory(categoryId, request);

        assertThat(categoryId).isEqualTo(category.getId());
        assertThat(request.getName()).isEqualTo(category.getName());
        assertThat(request.getDescription()).isEqualTo(category.getDescription());
    }
}