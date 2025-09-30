package com.fastcampus.blog.service;

import com.fastcampus.blog.entity.Post;
import com.fastcampus.blog.exception.ApiException;
import com.fastcampus.blog.repository.PostRepository;
import com.fastcampus.blog.request.CreatePostRequest;
import com.fastcampus.blog.response.PostCreateResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    public Iterable<Post> getPosts(String title, String body, Boolean published) {
        if (published != null) {
            // Filter by publication status
            if (title != null && body != null) {
                return postRepository.findByTitleContainingAndBodyContainingAndIsPublishedAndIsDeletedFalse(title, body, published);
            } else if (title != null) {
                return postRepository.findByTitleContainingAndIsPublishedAndIsDeletedFalse(title, published);
            } else if (body != null) {
                return postRepository.findByBodyContainingAndIsPublishedAndIsDeletedFalse(body, published);
            } else {
                return postRepository.findByIsPublishedAndIsDeletedFalse(published);
            }
        } else {
            // Existing logic when published filter is not provided
            if (title != null && body != null) {
                return postRepository.findByTitleContainingAndBodyContainingAndIsDeletedFalse(title, body);
            } else if (title != null) {
                return postRepository.findByTitleContainingAndIsDeletedFalse(title);
            } else if (body != null) {
                return postRepository.findByBodyContainingAndIsDeletedFalse(body);
            } else {
                return postRepository.findAll();
            }
        }
    }

    public Post getPostBySlug(String slug) {
        Post post = postRepository.findBySlugAndIsDeleted(slug, false).orElse(null);
        if (post == null) {
            throw new ApiException("Post not found", HttpStatus.NOT_FOUND);
        }
        return post;
    }

    public PostCreateResponse createPost(CreatePostRequest request) {
        Post post = new Post();
        post.setBody(request.getBody());
        post.setTitle(request.getTitle());
        post.setSlug(request.getSlug());
        post.setPublished(true);
        post.setPublishedAt(Instant.now().getEpochSecond());
        post.setCreatedAt(Instant.now().getEpochSecond());
        post.setCommentCount(0L);
        post =  postRepository.save(post);

        return PostCreateResponse.builder()
                .title(post.getTitle())
                .body(post.getBody())
                .slug(post.getSlug())
                .publishedAt(post.getPublishedAt())
                .commentCount(post.getCommentCount())
                .build();
    }

    public Post updatePostBySlug (String slug, Post sentPostByUser) {
        Post savedPost = postRepository.findBySlugAndIsDeleted(slug, false).orElse(null);
        if (savedPost == null ) {
            return null;
        }
        savedPost.setUpdatedAt(Instant.now().getEpochSecond());
        return postRepository.save(sentPostByUser);
    }

    public Boolean deletePostById (Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) {
            return false;
        }

        savedPost.setDeleted(true);
        postRepository.save(savedPost);
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
