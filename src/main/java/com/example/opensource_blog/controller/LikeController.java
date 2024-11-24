package com.example.opensource_blog.controller;

import com.example.opensource_blog.dto.request.RequestLike;
import com.example.opensource_blog.service.likes.PostLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class LikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/pushLike")
    public ResponseEntity<Void> pushLike(@Valid @RequestBody RequestLike requestLike){
       int postId = requestLike.postId();
       String userId = requestLike.userId();
       postLikeService.pushLike(postId,userId);
       return ResponseEntity.ok().build();
   }
   @GetMapping("/{postId}/count")
    public ResponseEntity<Integer> getPushCount(@PathVariable String postId) {
       int likeCount = postLikeService.getLikeCountByPostId(Integer.parseInt(postId));
       return ResponseEntity.status(HttpStatus.OK).body(likeCount);
   }
}
