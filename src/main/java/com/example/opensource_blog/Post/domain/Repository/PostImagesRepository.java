package com.example.opensource_blog.Post.domain.Repository;

import com.example.opensource_blog.Post.domain.entity.PostImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImagesRepository extends JpaRepository<PostImages, Integer> {
}
