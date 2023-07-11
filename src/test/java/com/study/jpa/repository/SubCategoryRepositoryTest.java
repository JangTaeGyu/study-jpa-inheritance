package com.study.jpa.repository;

import com.study.jpa.entity.CategoryType;
import com.study.jpa.entity.MainCategory;
import com.study.jpa.entity.SubCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubCategoryRepositoryTest {
    @Autowired private MainCategoryRepository mainCategoryRepository;
    @Autowired private SubCategoryRepository subCategoryRepository;

    @Test
    @DisplayName("2 Depth 카테고리 저장 하기")
    void save() {
        MainCategory mainCategory = new MainCategory("MAIN NAME", "MAIN DESCRIPTION");
        mainCategoryRepository.save(mainCategory);

        // @ManyToOne(cascade = CascadeType.ALL) 설정을 하지 않아 부모 카테고리 별도 저장
        SubCategory subCategory = new SubCategory("SUB NAME", "SUB DESCRIPTION", mainCategory);
        subCategoryRepository.save(subCategory);

        assertThat(subCategory.getId()).isNotNull();
        assertThat(subCategory.getType()).isEqualTo(CategoryType.SUB);
        assertThat(subCategory.getParentCategory().getId()).isNotNull();
        assertThat(subCategory.getParentCategory().getType()).isEqualTo(CategoryType.MAIN);
    }
}