package com.example.opensource_blog.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.user JOIN FETCH c.post WHERE c.post.id = :postId")
    List<Comment> findAllByPostIdWithUserAndPost(@Param("postId") int postId);

    @Query("SELECT c FROM Comment c JOIN FETCH c.user JOIN FETCH c.post WHERE c.id = :id")
    Optional<Comment> findByIdWithUserAndPost(@Param("id") int id);

    @Query("SELECT c FROM Comment c JOIN c.user u WHERE u.userId = :userId")
    List<Comment> findByUserUsername(@Param("userId") String userId);

}