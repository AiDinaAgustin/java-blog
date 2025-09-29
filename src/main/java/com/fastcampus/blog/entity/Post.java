package com.fastcampus.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Post {
    @Id
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private boolean isPublished;
    private boolean isDeleted;
    private Integer createdAt;

    public void setPublished(boolean published) {
        isPublished = published;
    }
}
