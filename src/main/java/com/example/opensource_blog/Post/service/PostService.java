package com.example.opensource_blog.Post.service;

import com.example.opensource_blog.Post.domain.Repository.PostImagesRepository;
import com.example.opensource_blog.Post.domain.Repository.PostRepository;
import com.example.opensource_blog.Post.domain.entity.Post;
import com.example.opensource_blog.Post.dto.PostDTO;
import com.example.opensource_blog.Post.dto.request.PostRequestDTO;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostImagesRepository postImagesRepository;
    private final UserRepository userRepository;
    private final PostImageService postImageService;

    public PostService(PostRepository postRepository, PostImagesRepository postImagesRepository, UserRepository userRepository, PostImageService postImageService) {
        this.postRepository = postRepository;
        this.postImagesRepository = postImagesRepository;
        this.userRepository = userRepository;
        this.postImageService = postImageService;
    }

    @Transactional
    public Post createPost(PostRequestDTO postRequestDTO, List<MultipartFile> images, String userid) {
        // User 엔티티 조회
        UserAccount user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userid));

        // PostDTO를 Post 엔티티로 변환
        Post post = postRequestDTO.toEntity(user);
        // Post 저장
        Post savedPost = postRepository.save(post);
        // 이미지 파일 저장 처리
        postImageService.saveImages(images, savedPost);

        return savedPost;  // 저장된 Post 반환
    }

    @Transactional
    public Post updatePost(int id, PostRequestDTO postRequestDTO, List<MultipartFile> images, String userId) {
        // 기존 게시글 조회
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        if (!post.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        // 게시글 내용 업데이트
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setRestaurant(postRequestDTO.getRestaurant());

        // 이미지 업데이트
        // 기존 이미지 삭제 처리
//        post.getImages().clear();
//        // 기존 이미지 삭제
//        postImagesRepository.deleteAll(post.getImages());
//        post.getImages().clear();
//
//        // 새로운 이미지 저장
//        if (images != null && !images.isEmpty()) {
//            saveImages(images, post);
//        }
        if (images != null && !images.isEmpty()) {
            postImageService.updatePostImages(post, images);
        }


        return postRepository.save(post);
    }

    public void deletePost(int postId, String userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        if (!post.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        postImageService.deleteImagesByPost(post);
        // 게시글 삭제
        postRepository.deleteById(postId);
    }

    public Post getPostById(int postid) {
        return postRepository.findById(postid)
                .orElse(null); // 없는 경우 null 반환
    }
    public Post getPostWithImages(int id) {
        return getPostById(id);  // 추가 로직이 필요하면 확장 가능
    }

//    private void saveImages(List<MultipartFile> images, Post post) {
//        for (MultipartFile file : images) {
//            try {
//                PostImages image = new PostImages();
//                image.setPost(post);
//                image.setImageurl(file.getOriginalFilename());  // 파일명 저장
//                image.setImagedata(file.getBytes());  // 이미지 데이터 저장
//                postImagesRepository.save(image);
//                post.getImages().add(image);
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to save image file: " + file.getOriginalFilename(), e);
//            }
//        }
//    }
}
