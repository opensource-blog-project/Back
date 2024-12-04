package com.example.opensource_blog.controller;

import com.example.opensource_blog.config.SecurityTestConfig;
import com.example.opensource_blog.config.WithMockCustomUser;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.jwt.TokenProvider;
import com.example.opensource_blog.service.likes.PostLikeService;
import com.example.opensource_blog.service.user.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LikeController.class)
@Import(SecurityTestConfig.class)
class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostLikeService postLikeService;

    @MockBean
    private TokenProvider tokenProvider; // MockBean 추가

    private UserInfo mockUserInfo;


    @BeforeEach
    void setUp() {
        Mockito.reset(postLikeService);
        mockUserInfo = new UserInfo(new UserAccount("testUser", "password", "Test User"));
    }

    @Test
    @WithMockCustomUser(username = "adminUser", userId = "admin123")
    @DisplayName("PushLike 메서드가 성공적으로 수행되면 200code를 반환해야 한다.")
    void pushLike_ShouldReturnOk() throws Exception {
        int postId = 1;

        mockMvc.perform(post("/posts/{postId}/push-like", postId))
                .andExpect(status().isOk());

        verify(postLikeService, times(1)).pushLike(eq(postId), any(UserInfo.class));
    }

    @Test
    @WithMockCustomUser(username = "testUser", userId = "testUserId")
    @DisplayName("getPushCount 메서드를 호출하면 좋아요 수를 반환한다.")
    void getPushCount_ShouldReturnLikeCount() throws Exception {
        // Given
        int postId = 1;
        int likeCount = 5;
        when(postLikeService.getLikeCountByPostId(postId)).thenReturn(likeCount);

        // When & Then
        mockMvc.perform(get("/posts/{postId}/like-count", postId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(String.valueOf(likeCount)));

        verify(postLikeService, times(1)).getLikeCountByPostId(postId);
    }

}
