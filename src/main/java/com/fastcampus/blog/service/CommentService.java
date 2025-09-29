package com.fastcampus.blog.service;

import com.fastcampus.blog.entity.Comment;
import com.fastcampus.blog.entity.Post;
import com.fastcampus.blog.repository.CommentRepository;
import com.fastcampus.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Comment createComment (Comment comment) {
        comment.setCreatedAt(Instant.now().getEpochSecond());
        return commentRepository.save(comment);
    }
}
