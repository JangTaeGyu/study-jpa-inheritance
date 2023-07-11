package com.study.jpa.service;

import com.study.jpa.entity.Category;
import com.study.jpa.exception.CategoryNotFoundException;
import com.study.jpa.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
}
