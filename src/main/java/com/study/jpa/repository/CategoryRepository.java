package com.study.jpa.repository;

import com.study.jpa.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CategoryRepository extends Repository<Category, Long> {
    Optional<Category> findById(Long id);
    void deleteById(Long id);

    @Query(nativeQuery = true, value = "select exists(select 1 from categories where parent_main_id = :parentId or parent_sub_id = :parentId)")
    boolean existsChildrenByParentId(Long parentId);
}
