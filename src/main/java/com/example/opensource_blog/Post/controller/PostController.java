package com.example.opensource_blog.Post.controller;

import com.example.opensource_blog.Post.dto.PostDTO;
import com.example.opensource_blog.Post.domain.entity.Post;
import com.example.opensource_blog.Post.dto.request.PostRequestDTO;
import com.example.opensource_blog.Post.dto.response.PostResponseDTO;
import com.example.opensource_blog.Post.service.PostService;
import com.example.opensource_blog.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            HttpServletRequest request,
            @RequestPart("post") PostRequestDTO postRequestDTO,
            @RequestPart("images") List<MultipartFile> images) {

        // 요청 헤더에서 JWT 토큰 추출
        String token = getTokenFromRequest(request);

        // 토큰이 null인지, 그리고 유효한지 검증
        if (token == null || !tokenprovider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증 실패
        }

        // 토큰에서 사용자 아이디 추출
        String userid = tokenprovider.getAuthentication(token).getName();

        // PostDTO에서 Post 엔티티로 변환
//        Post post = postDTO.toEntity();

//        postRequestDTO.setU(userid);
        // 서비스 계층에서 게시글 및 이미지 저장 처리
        // 게시글 생성 처리
        Post createdPost = postService.createPost(postRequestDTO, images, userid);
        PostResponseDTO responseDTO = new PostResponseDTO(createdPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 게시글 수정
    @PutMapping("/{postid}/update")
    public ResponseEntity<PostResponseDTO> updatePost(
            HttpServletRequest request,
            @PathVariable int postid,
            @RequestPart("post")  PostRequestDTO postRequestDTO,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        // 요청 헤더에서 JWT 토큰 추출
        String token = getTokenFromRequest(request);

        // 토큰 유효성 검사
        if (token == null || !tokenprovider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증 실패
        }

        // 토큰에서 사용자 이름(아이디) 추출
        String userid = tokenprovider.getAuthentication(token).getName();

        // DB에서 기존 게시글 조회
        Post existingPost = postService.getPostById(postid);
        if (existingPost == null) {
            return ResponseEntity.notFound().build(); // 게시글이 없으면 404 반환
        }

        // 작성자 검증
        if (!existingPost.getUser().getUserId().equals(userid)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 작성자가 아니면 403 반환
        }

        if (existingPost == null) {
            return ResponseEntity.notFound().build(); // 게시글이 없으면 404 반환
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
        Post updatedPost = postService.updatePost(postid, postRequestDTO, images, userid);
        PostResponseDTO responseDTO = new PostResponseDTO(updatedPost);

        return ResponseEntity.ok(responseDTO);
    }

    // 게시글 삭제
    @DeleteMapping("/{postid}/delete")
    public ResponseEntity<Void> deletePost(HttpServletRequest request,@PathVariable int postid) {

        // 요청 헤더에서 JWT 토큰 추출
        String token = getTokenFromRequest(request);

        // 토큰 유효성 검사
        if (token == null || !tokenprovider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증 실패
        }

        // 토큰에서 사용자 아이디 추출
        String userid = tokenprovider.getAuthentication(token).getName();

        // DB에서 기존 게시글 조회
        Post existingPost = postService.getPostById(postid);
        if (existingPost == null) {
            return ResponseEntity.notFound().build(); // 게시글이 없으면 404 반환
        }

        // 작성자 검증
        if (!existingPost.getUser().getUserId().equals(userid)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 작성자가 아니면 403 반환
        }


        postService.deletePost(postid,userid);
        return ResponseEntity.noContent().build();
    }


    // 게시글 조회 (이미지 포함)
    @GetMapping("/{postid}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable int postid) {
        Post post = postService.getPostWithImages(postid);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        PostResponseDTO responseDTO = new PostResponseDTO(post);
        return ResponseEntity.ok(responseDTO);
    }

    //JWT 토큰 추출 메서드
    private String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer "를 제거하고 토큰만 반환
        }
        return null;
    }

}
