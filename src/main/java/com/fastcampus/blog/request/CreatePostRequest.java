package com.fastcampus.blog.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreatePostRequest {

    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;
    @Size(min = 5, message = "Body must be at least 5 characters long")
    private String body;
    @Size(min = 3, message = "Slug must be at least 3 characters long")
    private String slug;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSlug() {
        return slug;
    }
}
