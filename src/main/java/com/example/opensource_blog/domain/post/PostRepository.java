package com.example.opensource_blog.domain.post;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @EntityGraph(attributePaths = {"images"})
    Optional<Post> findById(int id);

    @Query("SELECT p FROM Post p JOIN FETCH p.user")
    Page<Post> findAllWithUser(Pageable pageable);

    @Query(value = "SELECT b FROM Post b JOIN FETCH b.member " +
            "WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword% OR b.member.username LIKE %:keyword%")
    Page<Post> searchByKeyword(String keyword, Pageable pageable);

//    // 제목 검색
//    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user WHERE p.title LIKE %:title%")
//    Page<Post> searchByTitle(String title, Pageable pageable);
//
//    // 내용 검색
//    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user WHERE p.content LIKE %:content%")
//    Page<Post> searchByContent(String content, Pageable pageable);
//
//    // 작성자 검색
//    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user WHERE p.user.username LIKE %:username%")
//    Page<Post> searchByUsername(String username, Pageable pageable);

}
