package com.study.jpa.repository;

import com.study.jpa.entity.CategoryType;
import com.study.jpa.entity.MainCategory;
import com.study.jpa.entity.SubCategory;
import com.study.jpa.entity.SubSubCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubSubCategoryRepositoryTest {
    @Autowired private MainCategoryRepository mainCategoryRepository;
    @Autowired private SubCategoryRepository subCategoryRepository;
    @Autowired private SubSubCategoryRepository subSubCategoryRepository;

    @Test
    @DisplayName("3 Depth 카테고리 저장 하기")
    void save() {
        MainCategory mainCategory = new MainCategory("MAIN NAME", "MAIN DESCRIPTION");
        mainCategoryRepository.save(mainCategory);

        // @ManyToOne(cascade = CascadeType.ALL) 설정을 하지 않아 부모 카테고리 별도 저장
        SubCategory subCategory = new SubCategory("SUB NAME", "SUB DESCRIPTION", mainCategory);
        subCategoryRepository.save(subCategory);

        // @ManyToOne(cascade = CascadeType.ALL) 설정을 하지 않아 부모 카테고리 별도 저장
        SubSubCategory subSubCategory = new SubSubCategory("SUB SUB NAME", "SUB SUB DESCRIPTION", subCategory);
        subSubCategoryRepository.save(subSubCategory);

        assertThat(subSubCategory.getId()).isNotNull();
        assertThat(subSubCategory.getType()).isEqualTo(CategoryType.SUBSUB);
        assertThat(subSubCategory.getParentCategory().getId()).isNotNull();
        assertThat(subSubCategory.getParentCategory().getType()).isEqualTo(CategoryType.SUB);
        assertThat(subSubCategory.getParentCategory().getParentCategory().getId()).isNotNull();
        assertThat(subSubCategory.getParentCategory().getParentCategory().getType()).isEqualTo(CategoryType.MAIN);
    }
}