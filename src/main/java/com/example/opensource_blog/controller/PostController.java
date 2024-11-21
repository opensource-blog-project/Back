package com.example.opensource_blog.controller;

import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.dto.request.PostRequestDTO;
import com.example.opensource_blog.dto.response.PostResponseDTO;
import com.example.opensource_blog.service.post.PostService;
import com.example.opensource_blog.jwt.TokenProvider;
import com.example.opensource_blog.service.user.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private TokenProvider tokenprovider; //jwt 유틸리티


    //게시글 생성
    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> createPost(
            @AuthenticationPrincipal UserInfo userInfo,
            @RequestPart("post") PostRequestDTO postRequestDTO,
            @RequestPart("images") List<MultipartFile> images) {

        Post createdPost = postService.createPost(postRequestDTO, images, userInfo);
        PostResponseDTO responseDTO = new PostResponseDTO(createdPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 게시글 수정
    @PutMapping("/{postId}/update")
    public ResponseEntity<PostResponseDTO> updatePost(
            @AuthenticationPrincipal UserInfo userInfo,
            @PathVariable int postId,
            @RequestPart("post")  PostRequestDTO postRequestDTO,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        // DB에서 기존 게시글 조회
        Post existingPost = postService.getPostById(postId);
        if (existingPost == null) {
            return ResponseEntity.notFound().build(); // 게시글이 없으면 404 반환
        }

        // 작성자 검증
        if (!existingPost.getUser().getUserId().equals(userInfo.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 작성자가 아니면 403 반환
        }

        // 전달된 데이터로 기존 게시글 업데이트
        if (postRequestDTO.getTitle() != null) {
            existingPost.setTitle(postRequestDTO.getTitle());
        }
        if (postRequestDTO.getContent() != null) {
            existingPost.setContent(postRequestDTO.getContent());
        }
        if (postRequestDTO.getRestaurant() != null) {
            existingPost.setRestaurant(postRequestDTO.getRestaurant());
        }

        // 이미지 URL 업데이트 처리
        Post updatedPost = postService.updatePost(postId, postRequestDTO, images, userInfo);
        PostResponseDTO responseDTO = new PostResponseDTO(updatedPost);

        return ResponseEntity.ok(responseDTO);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<Void> deletePost(
            @AuthenticationPrincipal UserInfo userInfo,
            @PathVariable int postId) {

        // DB에서 기존 게시글 조회
        Post existingPost = postService.getPostById(postId);
        if (existingPost == null) {
            return ResponseEntity.notFound().build(); // 게시글이 없으면 404 반환
        }

        // 작성자 검증
        if (!existingPost.getUser().getUserId().equals(userInfo.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 작성자가 아니면 403 반환
        }

        postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }


    // 게시글 조회 (이미지 포함)
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(
            @PathVariable int postId) {

        Post post = postService.getPostWithImages(postId);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        PostResponseDTO responseDTO = new PostResponseDTO(post);
        return ResponseEntity.ok(responseDTO);
    }

}
