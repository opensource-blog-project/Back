package com.example.opensource_blog.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @EntityGraph(attributePaths = {"images"})
    Optional<Post> findById(int id);

    @Query("SELECT p FROM Post p JOIN FETCH p.user")
    List<Post> findAllWithUser();


    @Query("SELECT p FROM Post p JOIN p.user u WHERE u.userId = :userId")
    List<Post> findByUser_Id(@Param("userId") String userId);


    @Query("SELECT p FROM Post p JOIN p.likes l WHERE l.user.userId = :userId")
    List<Post> findLikedPostsByUser(@Param("userId") String username);


}
