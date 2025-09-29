package com.fastcampus.blog.response;

import com.fastcampus.blog.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.ErrorResponse;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateResponse {

    private String title;
    private String body;

    private String slug;
    private Long publishedAt;

    private Long commentCount;
}
