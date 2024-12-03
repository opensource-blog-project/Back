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
//    Page<Post> findAllWithUser(Pageable pageable);



    @Query("SELECT p FROM Post p JOIN p.user u WHERE u.userId = :userId")
    Page<Post> findByUser_Id(@Param("userId") String userId, Pageable pageable);


    @Query("SELECT p FROM Post p JOIN p.likes l WHERE l.user.userId = :userId")
    Page<Post> findLikedPostsByUser(@Param("userId") String username, Pageable pageable);


}
