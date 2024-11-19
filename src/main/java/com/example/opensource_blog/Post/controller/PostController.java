package com.example.opensource_blog.Post.controller;

import com.example.opensource_blog.Post.dto.PostDTO;
import com.example.opensource_blog.Post.domain.entity.Post;
import com.example.opensource_blog.Post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;


    //게시글 생성
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(
            @RequestPart("post") PostDTO postDTO,
            @RequestPart("images") List<MultipartFile> images) {
//        String token = getTokenFromRequest(request);
//
//        // 토큰이 null인지, 그리고 토큰이 유효한지 검증
//        if (token == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증 실패
//        }

        // 토큰에서 사용자 이름 추출


        // 토큰 유효성 검증


        // PostDTO에서 Post 엔티티로 변환
        Post post = postDTO.toEntity();

        // 서비스 계층에서 게시글 및 이미지 저장 처리
        Post createdPost = postService.createPost(post, images);

        return ResponseEntity.ok(createdPost);
    }

    // 게시글 수정
    @PutMapping("/{postid}/update")
    public ResponseEntity<Post> updatePost(
            @PathVariable int postid,
            @RequestPart("post") PostDTO postDTO,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        // DB에서 기존 게시글을 조회
        Post existingPost = postService.getPostById(postid);

        if (existingPost == null) {
            return ResponseEntity.notFound().build(); // 게시글이 없으면 404 반환
        }

        // 전달된 데이터로 기존 게시글 업데이트
        if (postDTO.getTitle() != null) {
            existingPost.setTitle(postDTO.getTitle());
        }
        if (postDTO.getContent() != null) {
            existingPost.setContent(postDTO.getContent());
        }
        if (postDTO.getRestaurant() != null) {
            existingPost.setRestaurant(postDTO.getRestaurant());
        }

        // 이미지 URL 업데이트 처리
        Post updatedPost = postService.updatePost(postid, existingPost, images);

        return ResponseEntity.ok(updatedPost); // 업데이트된 게시글 반환
    }

    // 게시글 삭제
    @DeleteMapping("/{postid}/delete")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // JWT 토큰 추출 메서드
//    private String getTokenFromRequest(HttpServletRequest request) {
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7); // "Bearer "를 제거하고 토큰만 반환
//        }
//        return null;
//    }

    // 게시글 조회 (이미지 포함)
    @GetMapping("/{postid}")
    public ResponseEntity<Post> getPost(@PathVariable int postid) {
        Post post = postService.getPostWithImages(postid);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }
}
