package com.example.opensource_blog.service.post;

import com.example.opensource_blog.domain.hashtag.*;
import com.example.opensource_blog.domain.post.PostImagesRepository;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.dto.request.PostRequestDTO;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.response.PostListResponseDTO;
import com.example.opensource_blog.service.user.UserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;
    private final PostImagesRepository postImagesRepository;
    private final UserRepository userRepository;
    private final PostImageService postImageService;
    private final PostHashTagRepository postHashTagRepository;


    @Transactional
    public List<PostListResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAllWithUser();
        List<PostListResponseDTO> postList = posts.stream()
                .map(PostListResponseDTO::fromEntity) // 생성자를 통해 DTO 변환
                .collect(Collectors.toList());
        return postList;
    }

    @Transactional
    public Post createPost(PostRequestDTO postRequestDTO, List<MultipartFile> images, UserInfo userInfo) {
        // PostDTO를 Post 엔티티로 변환
        Post post = postRequestDTO.toEntity(postRequestDTO);
        // UserInfo에서 제공된 username으로 현재 로그인된 사용자 정보를 조회
        UserAccount currentUser = userRepository.findByUserId(userInfo.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + userInfo.getUsername()));
        // 조회한 사용자 정보를 Post 엔티티에 설정
        post.setUser(currentUser);
        // Post 저장
        Post savedPost = postRepository.save(post);
        //해시태그 id로 해시태그 저장
        postRequestDTO.getHashTagIds().stream().forEach(hashtagId -> {
            HashTag hashTag = hashTagRepository.findById(hashtagId).orElseThrow(() -> new IllegalArgumentException("hashTag not found" + hashtagId));
            PostHashTag postHashTag = PostHashTag.of(post, hashTag);
            postHashTagRepository.save(postHashTag);
        });
        // 이미지 파일 저장 처리
        postImageService.saveImages(images, savedPost);

        return savedPost;  // 저장된 Post 반환
    }

    @Transactional
    public Post updatePost(int id, PostRequestDTO postRequestDTO, List<MultipartFile> images, UserInfo userInfo) {
        // 기존 게시글 조회
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        // PostDTO를 Post 엔티티로 변환
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

    public void deletePost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        // 이미지 삭제
        postImageService.deleteImagesByPost(post);
        // 게시글 삭제
        postRepository.deleteById(postId);
    }

    public Post getPostById(int postId) {
        return postRepository.findById(postId)
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
//                image.setImageUrl(file.getOriginalFilename());  // 파일명 저장
//                image.setImagedata(file.getBytes());  // 이미지 데이터 저장
//                postImagesRepository.save(image);
//                post.getImages().add(image);
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to save image file: " + file.getOriginalFilename(), e);
//            }
//        }
//    }
}
