package com.example.opensource_blog.service.post;

import com.example.opensource_blog.domain.post.PostImagesRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostImages;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostImageService {

    private final PostImagesRepository postImagesRepository;



    public void saveImages(List<MultipartFile> images, Post post)  {
        for (MultipartFile file : images) {
            try {
                PostImages image = new PostImages();
                image.setPost(post);
                image.setImageUrl(file.getOriginalFilename());  // 파일명 저장
                image.setImageData(file.getBytes());  // 이미지 데이터 저장
                postImagesRepository.save(image);
                post.getImages().add(image);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image file: " + file.getOriginalFilename(), e);
            }
        }
    }

    @Transactional
    public void updatePostImages(Post post, List<MultipartFile> newImages) {
        // 기존 이미지 삭제
        deleteImagesByPost(post);

        // 새 이미지 저장
        saveImages(newImages, post);
    }

    @Transactional
    public void deleteImagesByPost(Post post) {
        List<PostImages> images = post.getImages();
        if (!images.isEmpty()) {
            postImagesRepository.deleteAll(images);
            post.getImages().clear();
        }
    }

}
