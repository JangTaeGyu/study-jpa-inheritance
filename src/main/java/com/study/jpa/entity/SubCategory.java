package com.study.jpa.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DiscriminatorValue("SUB")
@DynamicUpdate
public class SubCategory extends Category {
    @ManyToOne
    @JoinColumn(name = "parent_main_id")
    private MainCategory parentCategory;

    protected SubCategory() {}

    public SubCategory(String name, String description, MainCategory parentCategory) {
        super(name, description);
        this.parentCategory = parentCategory;
    }

    @Override
    public CategoryType getType() {
        return CategoryType.SUB;
    }
}
