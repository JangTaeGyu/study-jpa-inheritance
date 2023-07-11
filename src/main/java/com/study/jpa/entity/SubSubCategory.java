package com.study.jpa.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
@DiscriminatorValue("SUBSUB")
@DynamicUpdate
public class SubSubCategory extends Category {
    @ManyToOne
    @JoinColumn(name = "parent_sub_id")
    private SubCategory parentCategory;

    protected SubSubCategory() {}

    public SubSubCategory(String name, String description, SubCategory parentCategory) {
        super(name, description);
        this.parentCategory = parentCategory;
    }

    @Override
    public CategoryType getType() {
        return CategoryType.SUBSUB;
    }
}