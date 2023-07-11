package com.study.jpa.repository;

import com.study.jpa.entity.SubSubCategory;
import org.springframework.data.repository.Repository;

public interface SubSubCategoryRepository extends Repository<SubSubCategory, Long> {
    void save(SubSubCategory category);
    void deleteById(Long id);
}
