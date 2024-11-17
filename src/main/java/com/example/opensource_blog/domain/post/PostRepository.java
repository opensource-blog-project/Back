package com.example.opensource_blog.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    Optional<Post> findByPostId(int post_id);
    boolean existsByPostId(int post_id);
}
