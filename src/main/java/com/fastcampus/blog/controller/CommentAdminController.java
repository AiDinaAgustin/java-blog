package com.fastcampus.blog.controller;

import com.fastcampus.blog.entity.Comment;
import com.fastcampus.blog.request.CreateCommentRequest;
import com.fastcampus.blog.response.CreateCommentResponse;
import com.fastcampus.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/comments")
public class CommentAdminController {

    @Autowired
    CommentService commentService;

    @PostMapping
    public CreateCommentResponse createComment (@Valid @RequestBody CreateCommentRequest createCommentRequest) {
        return commentService.createComment(createCommentRequest);
    }
}
