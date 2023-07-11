package com.study.jpa.repository;

import com.study.jpa.entity.MainCategory;
import org.springframework.data.repository.Repository;

public interface MainCategoryRepository extends Repository<MainCategory, Long> {
    void save(MainCategory category);
    void deleteById(Long id);
}
