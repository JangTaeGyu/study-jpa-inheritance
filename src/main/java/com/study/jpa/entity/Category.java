package com.study.jpa.entity;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @Comment("카테고리 명")
    private String name;

    @Column(columnDefinition="TEXT")
    @Comment("카테고리 설명")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private CategoryType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Category() {}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
