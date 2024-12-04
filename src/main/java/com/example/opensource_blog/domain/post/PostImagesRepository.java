package com.example.opensource_blog.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImagesRepository extends JpaRepository<PostImages, Integer> {
}
