package com.fastcampus.blog.controller;

import com.fastcampus.blog.entity.Post;
import com.fastcampus.blog.request.CreatePostRequest;
import com.fastcampus.blog.response.PostCreateResponse;
import com.fastcampus.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/posts")
public class PostPublicController {

    @Autowired
    PostService postService;

    @GetMapping
    public Iterable<Post> getPost(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body,
            @RequestParam(required = false) Boolean published) {
        return postService.getPosts(title, body, published);
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

}
