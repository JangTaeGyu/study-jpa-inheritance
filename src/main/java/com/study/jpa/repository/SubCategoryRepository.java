package com.study.jpa.repository;

import com.study.jpa.entity.SubCategory;
import org.springframework.data.repository.Repository;

public interface SubCategoryRepository extends Repository<SubCategory, Long> {
    void save(SubCategory category);
    void deleteById(Long id);
}
