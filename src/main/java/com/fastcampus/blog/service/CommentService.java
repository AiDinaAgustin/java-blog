package com.fastcampus.blog.service;

import com.fastcampus.blog.entity.Comment;
import com.fastcampus.blog.entity.Post;
import com.fastcampus.blog.exception.ApiException;
import com.fastcampus.blog.repository.CommentRepository;
import com.fastcampus.blog.repository.PostRepository;
import com.fastcampus.blog.request.CreateCommentRequest;
import com.fastcampus.blog.response.CreateCommentResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public Iterable<Comment> getComments(String postSlug, Integer pageNo, Integer limit) {
        Post post = postRepository.findBySlugAndIsDeleted(postSlug, false).orElse(null);

        if (post == null) {
            return null;
        }

        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        return commentRepository.findByPostId(post.getId(), pageRequest).getContent();
    }

    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        Post post = postRepository.findBySlugAndIsDeleted(request.getPost().getSlug(), false).orElse(null);
        if (post == null) {
            throw new ApiException("Post not found", HttpStatus.NOT_FOUND);
        }

        // Create Comment entity from request
        Comment comment = new Comment();
        comment.setName(request.getName());
        comment.setEmail(request.getEmail());
        comment.setBody(request.getBody());
        comment.setPost(post);
        comment.setCreatedAt(Instant.now().getEpochSecond());

        // Save comment
        Comment savedComment = commentRepository.save(comment);

        // Update post comment count
        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        // Map to response
        return CreateCommentResponse.builder()
                .name(savedComment.getName())
                .email(savedComment.getEmail())
                .body(savedComment.getBody())
                .post(savedComment.getPost())
                .build();
    }
}
