package com.fastcampus.blog.repository;

import com.fastcampus.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    Optional<Post> findBySlug(String slug);
    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);

    // Add search methods
    Page<Post> findByTitleContainingAndIsDeletedFalse(String title, Pageable pageable);
    Page<Post> findByBodyContainingAndIsDeletedFalse(String body, Pageable pageable);
    Page<Post> findByTitleContainingAndBodyContainingAndIsDeletedFalse(String title, String body, Pageable pageable);

    // For public API - only return published posts
    Page<Post> findByTitleContainingAndIsPublishedTrueAndIsDeletedFalse(String title, Pageable pageable);
    Page<Post> findByBodyContainingAndIsPublishedTrueAndIsDeletedFalse(String body, Pageable pageable);
    Page<Post> findByTitleContainingAndBodyContainingAndIsPublishedTrueAndIsDeletedFalse(String title, String body, Pageable pageable);

    // Add these methods to PostRepository
    Page<Post> findByIsPublishedAndIsDeletedFalse(boolean isPublished, Pageable pageable);
    Page<Post> findByTitleContainingAndIsPublishedAndIsDeletedFalse(String title, boolean isPublished, Pageable pageable);
    Page<Post> findByBodyContainingAndIsPublishedAndIsDeletedFalse(String body, boolean isPublished, Pageable pageable);
    Page<Post> findByTitleContainingAndBodyContainingAndIsPublishedAndIsDeletedFalse(String title, String body, boolean isPublished, Pageable pageable);
}
