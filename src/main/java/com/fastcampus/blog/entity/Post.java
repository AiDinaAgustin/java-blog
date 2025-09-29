package com.fastcampus.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;

    @Column(unique = true)
    private String slug;
    private boolean isPublished;
    private boolean isDeleted;
    private Long createdAt;
    private Long updatedAt;
    private Long publishedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comment;
    private Long commentCount;

    public Integer getId() {
        return id;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public void setPublishedAt(Long publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getSlug() {
        return slug;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}
