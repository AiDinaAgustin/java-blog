package com.fastcampus.blog.repository;

import com.fastcampus.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    Optional<Post> findBySlug(String slug);
    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);

    // Add search methods
    List<Post> findByTitleContainingAndIsDeletedFalse(String title);
    List<Post> findByBodyContainingAndIsDeletedFalse(String body);
    List<Post> findByTitleContainingAndBodyContainingAndIsDeletedFalse(String title, String body);

    // For public API - only return published posts
    List<Post> findByTitleContainingAndIsPublishedTrueAndIsDeletedFalse(String title);
    List<Post> findByBodyContainingAndIsPublishedTrueAndIsDeletedFalse(String body);
    List<Post> findByTitleContainingAndBodyContainingAndIsPublishedTrueAndIsDeletedFalse(String title, String body);
}
