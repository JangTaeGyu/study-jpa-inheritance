package com.study.jpa.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("MAIN")
@DynamicUpdate
public class MainCategory extends Category {
    protected MainCategory() {}

    public MainCategory(String name, String description) {
        super(name, description);
    }

    @Override
    public CategoryType getType() {
        return CategoryType.MAIN;
    }
}
