package com.example.opensource_blog.controller;

import com.example.opensource_blog.service.comment.CommentService;
import com.example.opensource_blog.dto.request.ReqCommentDto;
import com.example.opensource_blog.dto.response.ResCommentDto;
import com.example.opensource_blog.service.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/list")
    public ResponseEntity<List<ResCommentDto>> commentList(
            @PathVariable int postId) {

        List<ResCommentDto> commentList = commentService.getAllComments(postId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @PostMapping("/create")
    public ResponseEntity<ResCommentDto> create(
            @AuthenticationPrincipal UserInfo userInfo,
            @PathVariable int postId,
            @RequestBody ReqCommentDto reqCommentDto) {

        ResCommentDto saveCommentDTO = commentService.create(postId, userInfo, reqCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCommentDTO);
    }

    @PutMapping("/{commentId}/update")
    public ResponseEntity<ResCommentDto> update(
            @PathVariable int commentId,
            @RequestBody ReqCommentDto reqCommentDto) {

        ResCommentDto updateCommentDTO = commentService.update(commentId, reqCommentDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateCommentDTO);
    }

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<Long> delete(@PathVariable int commentId) {

        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}