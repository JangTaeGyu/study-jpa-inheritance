package com.study.jpa.repository;

import com.study.jpa.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/sql/category-repository-test-data.sql")
class CategoryRepositoryTest {
    @Autowired private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 아이디로 카테고리 찾기")
    void findByCategoryId() {
        Optional<Category> result = categoryRepository.findById(1L);
        assertThat(result.isPresent()).isTrue();

        Category category = result.get();
        assertThat(category.getId()).isNotNull();
    }

    @Test
    @DisplayName("잘못된 카테고리 아이디로 카테고리 찾기")
    void failedToFindByCategoryId() {
        Optional<Category> result = categoryRepository.findById(0L);
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("카테고리 삭제")
    void deleteById() {
        categoryRepository.deleteById(1L);
    }

    @Test
    @DisplayName("자식 카테고리 존재 여부")
    void existsChildrenByParentId() {
        boolean exists = categoryRepository.existsChildrenByParentId(1L);
        assertThat(exists).isFalse();
    }
}