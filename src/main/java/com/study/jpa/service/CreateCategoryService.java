package com.study.jpa.service;

import com.study.jpa.entity.*;
import com.study.jpa.exception.CategoryNotFoundException;
import com.study.jpa.repository.CategoryRepository;
import com.study.jpa.repository.MainCategoryRepository;
import com.study.jpa.repository.SubCategoryRepository;
import com.study.jpa.repository.SubSubCategoryRepository;
import com.study.jpa.service.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final SubSubCategoryRepository subSubCategoryRepository;

    private Category makeCategoryEntity(CreateCategoryRequest request) {
        if (request.getParentId() == null) return new MainCategory(request.getName(), request.getDescription());

        Category category = categoryRepository.findById(request.getParentId())
                .orElseThrow(CategoryNotFoundException::new);

        return switch (category.getType()) {
            case MAIN -> new SubCategory(request.getName(), request.getDescription(), (MainCategory) category);
            case SUB -> new SubSubCategory(request.getName(), request.getDescription(), (SubCategory) category);
            case SUBSUB -> throw new RuntimeException("하위 카테고리를 등록 할 수 없습니다.");
        };
    }

    private void saveCategory(Category category) {
        switch (category.getType()) {
            case MAIN -> mainCategoryRepository.save((MainCategory) category);
            case SUB -> subCategoryRepository.save((SubCategory) category);
            case SUBSUB -> subSubCategoryRepository.save((SubSubCategory) category);
        };
    }

    public Category createCategory(CreateCategoryRequest request) {
        Category category = makeCategoryEntity(request);
        saveCategory(category);
        return category;
    }
}
