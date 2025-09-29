package com.fastcampus.blog.service;

import com.fastcampus.blog.entity.Post;
import com.fastcampus.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    @Autowired
    PostRepository postRepository;

    Post post1 = new Post();
    Post post2 = new Post();

    List<Post> posts = new ArrayList<Post>(Arrays.asList(post1, post2));

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostBySlug(String slug) {
        return postRepository.findBySlug(slug).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePostBySlug (String slug, Post sentPostByUser) {
        Post savedPost = postRepository.findBySlug(slug).orElse(null);
        if (savedPost == null ) {
            return null;
        }

        return postRepository.save(sentPostByUser);
    }

    public Boolean deletePostById (Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) {
            return false;
        }

        postRepository.deleteById(id);
        return true;
    }

    public Post publishPostById(Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setPublished(true);
        return postRepository.save(savedPost);
    }
}
