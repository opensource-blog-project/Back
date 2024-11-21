package com.example.opensource_blog.domain.post;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @EntityGraph(attributePaths = {"images"})
    Optional<Post> findById(int id);
}
