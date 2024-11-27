package com.example.opensource_blog.controller;

import com.example.opensource_blog.domain.hashtag.HashTagDto;
import com.example.opensource_blog.dto.response.PostListResponseDTO;
import com.example.opensource_blog.service.hashtag.HashTageService;
import com.example.opensource_blog.service.hashtag.PostHashTageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("")
@RestController
public class HashTagController {
    private final HashTageService hashTageService;
    private final PostHashTageService postHashTageService;

    @GetMapping("/hashTags")
    public ResponseEntity<List<HashTagDto>> getHashTags() {
        List<HashTagDto> hashTags = hashTageService.getAllHashTag();
        return ResponseEntity.status(HttpStatus.OK).body(hashTags);
    }

}
