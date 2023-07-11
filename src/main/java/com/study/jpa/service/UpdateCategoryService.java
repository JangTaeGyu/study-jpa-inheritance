package com.study.jpa.service;

import com.study.jpa.entity.Category;
import com.study.jpa.exception.CategoryNotFoundException;
import com.study.jpa.repository.CategoryRepository;
import com.study.jpa.service.request.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category updateCategory(Long categoryId, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        category.update(request.getName(), request.getDescription());
        return category;
    }
}
