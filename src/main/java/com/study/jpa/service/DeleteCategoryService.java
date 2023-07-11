package com.study.jpa.service;

import com.study.jpa.entity.Category;
import com.study.jpa.exception.CategoryNotFoundException;
import com.study.jpa.exception.ExistsChildrenCategoryException;
import com.study.jpa.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        if (categoryRepository.existsChildrenByParentId(category.getId())) throw new ExistsChildrenCategoryException();
        categoryRepository.deleteById(category.getId());
    }
}
