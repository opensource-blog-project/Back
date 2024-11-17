package com.example.opensource_blog.controller;

import com.example.opensource_blog.dto.request.RequestLike;
import com.example.opensource_blog.service.likes.PostLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/pushLike")
@RestController
public class LikeController {
    private final PostLikeService postLikeService;

    @PostMapping("")
    public ResponseEntity<Void> pushLike(@Valid @RequestBody RequestLike requestLike){
       int postId = requestLike.postId();
       String username = requestLike.username();
       postLikeService.pushLike(postId,username);
       return ResponseEntity.ok().build();
   }
}
