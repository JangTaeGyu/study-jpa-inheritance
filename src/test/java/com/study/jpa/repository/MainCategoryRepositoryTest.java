package com.study.jpa.repository;

import com.study.jpa.entity.CategoryType;
import com.study.jpa.entity.MainCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MainCategoryRepositoryTest {
    @Autowired private MainCategoryRepository mainCategoryRepository;

    @Test
    @DisplayName("메인 카테고리 저장 하기")
    void save() {
        MainCategory category = new MainCategory("MAIN NAME", "MAIN DESCRIPTION");
        mainCategoryRepository.save(category);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getType()).isEqualTo(CategoryType.MAIN);
    }
}