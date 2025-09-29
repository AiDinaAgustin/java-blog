package com.fastcampus.blog.entity;

import lombok.Data;

@Data
public class Post {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private boolean isPublished;
    private boolean isDeleted;
    private Integer createdAt;
    private Integer publishedAt;

    public Post(Integer id, String title, String body, String slug){
        this.id = id;
        this.title = title;
        this.body = body;
        this.slug = slug;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }
}
