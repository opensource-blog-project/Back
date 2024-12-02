package com.example.opensource_blog.controller;


import com.example.opensource_blog.config.SecurityTestConfig;
import com.example.opensource_blog.domain.hashtag.HashTagDto;
import com.example.opensource_blog.domain.hashtag.HashTagType;
import com.example.opensource_blog.jwt.TokenProvider;
import com.example.opensource_blog.service.hashtag.HashTageService;
import com.example.opensource_blog.service.hashtag.PostHashTageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HashTagController.class)
@Import(SecurityTestConfig.class)
class HashTagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HashTageService hashTageService;

    @MockBean
    private PostHashTageService postHashTageService;

    @MockBean
    private TokenProvider tokenProvider; // TokenProvider를 Mock 처리

    @BeforeEach
    void setUp() {
        Mockito.reset(hashTageService, postHashTageService);
    }

    @Test
    @DisplayName("getHashTags 메서드를 호출하면 해시태그 리스트를 반환해야한다.")
    void getHashTags_ShouldReturnHashTagsList_WhenInvoked() throws Exception {
        // Given
        HashTagDto hashTag1 = new HashTagDto(1L, HashTagType.CATEGORY, "Tag1");
        HashTagDto hashTag2 = new HashTagDto(2L, HashTagType.VISIT_PURPOSE, "Tag2");

        List<HashTagDto> mockHashTags = Arrays.asList(hashTag1, hashTag2);
        when(hashTageService.getAllHashTag()).thenReturn(mockHashTags);

        // When & Then
        mockMvc.perform(get("/hashTags")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].hashTagId").value(1))
                .andExpect(jsonPath("$[0].hashTagType").value("CATEGORY"))
                .andExpect(jsonPath("$[0].name").value("Tag1"))
                .andExpect(jsonPath("$[1].hashTagId").value(2))
                .andExpect(jsonPath("$[1].hashTagType").value("VISIT_PURPOSE"))
                .andExpect(jsonPath("$[1].name").value("Tag2"));

        Mockito.verify(hashTageService, Mockito.times(1)).getAllHashTag();
    }

}
