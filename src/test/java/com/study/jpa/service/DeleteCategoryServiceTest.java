package com.study.jpa.service;

import com.study.jpa.exception.CategoryNotFoundException;
import com.study.jpa.exception.ExistsChildrenCategoryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/category-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class DeleteCategoryServiceTest {
    @Autowired private DeleteCategoryService deleteCategoryService;

    @Test
    @DisplayName("존재하지 않는 카테고리 삭제할 때 오류가 발생한다.")
    void notFoundCategory() {
        assertThrows(CategoryNotFoundException.class, () -> {
            deleteCategoryService.deleteCategory(0L);
        });
    }

    @Test
    @DisplayName("자식 카테고리가 존재하면 카테고리를 삭제 할 수 없다.")
    void existsChildrenCategory() {
        assertThrows(ExistsChildrenCategoryException.class, () -> {
            deleteCategoryService.deleteCategory(1L);
        });
    }

    @Test
    @DisplayName("카테고리 삭제한다.")
    void deleteCategory() {
        deleteCategoryService.deleteCategory(3L);
    }
}