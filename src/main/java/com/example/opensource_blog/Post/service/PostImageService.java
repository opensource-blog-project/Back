package com.example.opensource_blog.Post.service;

import com.example.opensource_blog.Post.domain.Repository.PostImagesRepository;
import com.example.opensource_blog.Post.domain.entity.Post;
import com.example.opensource_blog.Post.domain.entity.PostImages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class PostImageService {

    private final PostImagesRepository postImagesRepository;



    public void saveImage(MultipartFile file, Post post) throws IOException {
        PostImages image = new PostImages();
        image.setPost(post);  // 관계 설정
        image.setImageurl(file.getOriginalFilename());  // 파일명 저장

        // 이미지 데이터 저장
        image.setImagedata(file.getBytes());

        postImagesRepository.save(image);
    }

}
