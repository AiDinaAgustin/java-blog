package com.fastcampus.blog.controller;

import com.fastcampus.blog.entity.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    Post post1 = new Post(1, "title1", "Title 1", "title-1");
    Post post2 = new Post(2, "title2", "Title 2", "title-2");

    List<Post> posts = List.of(post1, post2);
    @GetMapping("/")
    public List<Post> getPost() {
        return posts;
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return posts.stream().filter(post -> post.getSlug().equals(slug)).findFirst().orElse(null);
    }
}
