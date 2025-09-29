package com.fastcampus.blog.controller;

import com.fastcampus.blog.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {

    Post post1 = new Post(1, "title1", "Title 1", "title-1");
    Post post2 = new Post(2, "title2", "Title 2", "title-2");

    List<Post> posts = new ArrayList<Post>(Arrays.asList(post1, post2));
    @GetMapping("/")
    public List<Post> getPost() {
        return posts;
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return posts.stream().filter(post -> post.getSlug().equals(slug)).findFirst().orElse(null);
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/{slug}")
    public Post updatePostBySlug(@PathVariable String slug, @RequestBody Post sentPostByUser) {
        Post savedPost = posts.stream().
                filter(p -> p.getSlug().equals(slug)).findFirst().orElse(null);
        if (savedPost == null ) {
            return null;
        }

        savedPost.setTitle(sentPostByUser.getTitle());
        savedPost.setSlug(sentPostByUser.getSlug());
        savedPost.setBody(sentPostByUser.getBody());
        return savedPost;
    }

    @DeleteMapping("/{id}")
    public Boolean deletePostById(@PathVariable Integer id) {
        Post savedPost = posts.stream().
                filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if (savedPost == null) {
            return false;
        }

        posts.remove(savedPost);
        return true;
    }

    @PostMapping("/{id}/publish")
    public Post publishPostById(@PathVariable Integer id) {
        Post savedPost = posts.stream().
                filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setPublished(true);
        return savedPost;
    }
}
