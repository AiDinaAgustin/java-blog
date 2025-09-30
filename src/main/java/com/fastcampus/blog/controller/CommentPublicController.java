package com.fastcampus.blog.controller;

import com.fastcampus.blog.entity.Comment;
import com.fastcampus.blog.request.CreateCommentRequest;
import com.fastcampus.blog.response.CreateCommentResponse;
import com.fastcampus.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/comments")
public class CommentPublicController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public Iterable<Comment> getComment(@RequestParam String postSlug,
                                        @RequestParam Integer pageNo,
                                        @RequestParam Integer limit){
        return commentService.getComments(postSlug, pageNo, limit);
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Integer id){
        return commentService.getCommentById(id);
    }
}
