package com.example.opensource_blog.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.user JOIN FETCH c.post WHERE c.post.id = :postId")
    Page<Comment> findAllByPostIdWithUserAndPost(@Param("postId") int postId, Pageable pageable);

    @Query("SELECT c FROM Comment c JOIN FETCH c.user JOIN FETCH c.post WHERE c.id = :id")
    Optional<Comment> findByIdWithUserAndPost(@Param("id") int id);
}