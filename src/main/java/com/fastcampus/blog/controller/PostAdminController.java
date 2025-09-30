package com.fastcampus.blog.controller;

import com.fastcampus.blog.entity.Post;
import com.fastcampus.blog.request.CreatePostRequest;
import com.fastcampus.blog.response.PostCreateResponse;
import com.fastcampus.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/posts")
public class PostAdminController {

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

    @PostMapping
    public PostCreateResponse createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        return postService.createPost(createPostRequest);
    }

    @PutMapping("/{slug}")
    public Post updatePostBySlug(@PathVariable String slug, @RequestBody Post sentPostByUser) {
        return postService.updatePostBySlug(slug, sentPostByUser);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePostById(@PathVariable Integer id) {
        return postService.deletePostById(id);
    }

    @PostMapping("/{id}/publish")
    public Post publishPostById(@PathVariable Integer id) {
        return postService.publishPostById(id);
    }
}
