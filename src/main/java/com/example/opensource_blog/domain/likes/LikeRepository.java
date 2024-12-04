package com.example.opensource_blog.domain.likes;

import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.users.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByPostAndUser(Post post, UserAccount user);
    boolean existsByPostAndUser(Post post,UserAccount user);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.postId = :postId")
    int countByPost_id(@Param("postId")Integer postId);


}
