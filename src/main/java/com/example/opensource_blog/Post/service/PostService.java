package com.example.opensource_blog.Post.service;

import com.example.opensource_blog.Post.domain.Repository.PostImagesRepository;
import com.example.opensource_blog.Post.domain.Repository.PostRepository;
import com.example.opensource_blog.Post.domain.entity.Post;
import com.example.opensource_blog.Post.domain.entity.PostImages;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostImagesRepository postImagesRepository;

    public PostService(PostRepository postRepository, PostImagesRepository postImagesRepository) {
        this.postRepository = postRepository;
        this.postImagesRepository = postImagesRepository;
    }

    @Transactional
    public Post createPost(Post post, List<MultipartFile> images) {
        // Post 저장
        Post savedPost = postRepository.save(post);

        // 이미지 파일 저장 처리
        saveImages(images, savedPost);

        return savedPost;  // 저장된 Post 반환
    }

    @Transactional
    public Post updatePost(int id, Post postDetails, List<MultipartFile> images) {
        // 기존 게시글 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));

        // 게시글 내용 업데이트
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setRestaurant(postDetails.getRestaurant());

        // 이미지 업데이트
        // 기존 이미지 삭제 처리
        post.getImages().clear();
        // 기존 이미지 삭제
        postImagesRepository.deleteAll(post.getImages());
        post.getImages().clear();

        // 새로운 이미지 저장
        if (images != null && !images.isEmpty()) {
            saveImages(images, post);
        }

        return postRepository.save(post);
    }

    public void deletePost(int id) {
        // 삭제 전 게시글이 존재하는지 확인
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("Post not found with id: " + id);
        }

        // 게시글 삭제
        postRepository.deleteById(id);
    }

    public Post getPostById(int postid) {
        return postRepository.findById(postid)
                .orElse(null); // 없는 경우 null 반환
    }
    public Post getPostWithImages(int id) {
        return getPostById(id);  // 추가 로직이 필요하면 확장 가능
    }

    private void saveImages(List<MultipartFile> images, Post post) {
        for (MultipartFile file : images) {
            try {
                PostImages image = new PostImages();
                image.setPost(post);
                image.setImageurl(file.getOriginalFilename());  // 파일명 저장
                image.setImagedata(file.getBytes());  // 이미지 데이터 저장
                postImagesRepository.save(image);
                post.getImages().add(image);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image file: " + file.getOriginalFilename(), e);
            }
        }
    }
}
