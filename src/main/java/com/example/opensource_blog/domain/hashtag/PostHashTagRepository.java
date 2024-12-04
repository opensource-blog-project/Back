package com.example.opensource_blog.domain.hashtag;

import com.example.opensource_blog.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag,Long> {

    List<PostHashTag> findByPost(Post post);

    @Query("SELECT DISTINCT p FROM Post p JOIN FETCH p.postHashTags ph WHERE ph.hashTag.id = :hashTagId")
    List<Post> findPostsByHashTagId(@Param("hashTagId")Long hashTagId);

}
