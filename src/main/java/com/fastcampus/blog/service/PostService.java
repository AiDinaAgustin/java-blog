package com.fastcampus.blog.service;

import com.fastcampus.blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {
    Post post1 = new Post(1, "title1", "Title 1", "title-1");
    Post post2 = new Post(2, "title2", "Title 2", "title-2");

    List<Post> posts = new ArrayList<Post>(Arrays.asList(post1, post2));

    public List<Post> getPosts() {
        return posts;
    }

    public Post getPostBySlug(String slug) {
        return posts.stream().filter(post -> post.getSlug().equals(slug)).findFirst().orElse(null);
    }

    public Post createPost(Post post) {
        posts.add(post);
        return post;
    }

    public Post updatePostBySlug (String slug, Post sentPostByUser) {
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

    public Boolean deletePostById (Integer id) {
        Post savedPost = posts.stream().
                filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if (savedPost == null) {
            return false;
        }

        posts.remove(savedPost);
        return true;
    }

    public Post publishPostById(Integer id) {
        Post savedPost = posts.stream().
                filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setPublished(true);
        return savedPost;
    }
}
