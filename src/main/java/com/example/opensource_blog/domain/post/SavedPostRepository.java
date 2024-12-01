package com.example.opensource_blog.domain.post;

import com.example.opensource_blog.domain.users.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPostRepository extends JpaRepository<SavePost, Integer> {
    List<SavePost> findByUser(UserAccount user);

    boolean existsByUserAndPost(UserAccount user, Post post);

    @Modifying
    @Query("DELETE FROM SavePost sp WHERE sp.user.userId = :userId AND sp.post.postId = :postId")
    void deleteByPostIdAndUsername(@Param("userId")String userId,@Param("postId") Integer postId);

    @Query("SELECT sp.post FROM SavePost sp WHERE sp.user.userId = :userId")
    Page<Post> findSavedPostsByUser(@Param("userId") String userId, Pageable pageable);
}
