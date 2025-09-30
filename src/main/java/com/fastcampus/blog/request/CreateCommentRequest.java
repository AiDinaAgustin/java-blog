package com.fastcampus.blog.request;

import com.fastcampus.blog.entity.Post;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreateCommentRequest {

    @Size(min = 3, message = "Name must be at least 3 characters long")
    @NotNull
    private String name;

    @Size(min = 5, message = "Email must be at least 5 characters long")
    @Email
    @NotNull
    private String email;

    @NotNull
    private Post post;

    @Size(min = 5, message = "Body must be at least 5 characters long")
    @NotNull
    private String body;
}
