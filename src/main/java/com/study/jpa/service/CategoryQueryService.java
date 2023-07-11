package com.study.jpa.service;

import com.study.jpa.entity.Category;

public interface CategoryQueryService {
    Category getCategory(Long categoryId);
}
