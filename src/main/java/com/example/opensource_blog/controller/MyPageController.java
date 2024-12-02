package com.example.opensource_blog.controller;

import com.example.opensource_blog.dto.response.PostListResponseDTO;
import com.example.opensource_blog.dto.response.ResCommentDto;
import com.example.opensource_blog.service.comment.CommentService;
import com.example.opensource_blog.service.post.PostService;
import com.example.opensource_blog.service.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mypage")
public class MyPageController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    // 자신이 작성한 글 목록 조회

    @GetMapping("/my-posts")
    public ResponseEntity<Page<PostListResponseDTO>> getMyPosts(
            @AuthenticationPrincipal UserInfo userInfo,
            @PageableDefault(size = 10, sort = "postId", direction = Sort.Direction.DESC) Pageable pageable) {

        // 인증된 사용자 정보 확인
        if (userInfo != null) {
            log.info("Authenticated user: {}", userInfo.getUsername());
        } else {
            log.warn("No authenticated user found!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // user_id를 사용하여 로그인한 사용자의 글만 조회
        Page<PostListResponseDTO> myPosts = postService.getPostsByUser(userInfo.getUsername(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(myPosts);
    }



    // 자신이 좋아요한 글 목록 조회
    @GetMapping("/liked-posts")
    public ResponseEntity<Page<PostListResponseDTO>> getLikedPosts(
            @AuthenticationPrincipal UserInfo userInfo,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<PostListResponseDTO> likedPosts = postService.getLikedPostsByUser(userInfo.getUsername(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(likedPosts);
    }

    // 자신이 저장한 글 목록 조회
    @GetMapping("/saved-posts")
    public ResponseEntity<Page<PostListResponseDTO>> getSavedPosts(
            @AuthenticationPrincipal UserInfo userInfo,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<PostListResponseDTO> savedPosts = postService.getSavedPostsByUser(userInfo.getUsername(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(savedPosts);
    }

    // 자신이 남긴 댓글 목록 조회
    @GetMapping("/my-comments")
    public ResponseEntity<Page<ResCommentDto>> getMyComments(
            @AuthenticationPrincipal UserInfo userInfo,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ResCommentDto> myComments = commentService.getCommentsByUser(userInfo.getUsername(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(myComments);
    }
}

