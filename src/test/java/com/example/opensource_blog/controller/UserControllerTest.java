package com.example.opensource_blog.controller;


import com.example.opensource_blog.config.SecurityTestConfig;
import com.example.opensource_blog.dto.request.RequestJoin;
import com.example.opensource_blog.dto.request.RequestLogin;
import com.example.opensource_blog.dto.response.ResponseLogin;
import com.example.opensource_blog.execeptions.BadRequestException;
import com.example.opensource_blog.jwt.TokenProvider;
import com.example.opensource_blog.service.user.UserJoinService;
import com.example.opensource_blog.service.user.UserLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(SecurityTestConfig.class) // Security 설정 적용
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserLoginService loginService;

    @MockBean
    private UserJoinService joinService;

    @MockBean
    private TokenProvider tokenProvider; // TokenProvider MockBean 추가

    @BeforeEach
    void setUp() {
        Mockito.reset(loginService, joinService);
    }

    @Test
    @DisplayName("로그인이 성공한다면 토큰을 반환해야한다.")
    void authorize_ShouldReturnToken_WhenLoginValid() throws Exception {
        // Given
        RequestLogin requestLogin = new RequestLogin("testUser", "password123");
        ResponseLogin responseLogin = ResponseLogin.builder()
                .accessToken("mockAccessToken")
                .username("testUser")
                .build();

        when(loginService.authenticate(requestLogin.userId(), requestLogin.password())).thenReturn(responseLogin);

        // When & Then
        mockMvc.perform(post("/api/user/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "userId": "testUser",
                            "password": "password123"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.AUTHORIZATION, "Bearer mockAccessToken"))
                .andExpect(jsonPath("$.data.accessToken").value("mockAccessToken"))
                .andExpect(jsonPath("$.data.username").value("testUser"));

        verify(loginService, times(1)).authenticate("testUser", "password123");
    }

    @Test
    @DisplayName("로그인이 실패하면 400에러코드가 발생한다.")
    void authorize_ShouldReturnBadRequest_WhenLoginInvalid() throws Exception {
        // Given
        doThrow(new BadRequestException("Invalid login")).when(loginService).authenticate(anyString(), anyString());

        // When & Then
        mockMvc.perform(post("/api/user/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "userId": "",
                            "password": "short"
                        }
                        """))
                .andExpect(status().isBadRequest());

        verify(loginService, never()).authenticate(anyString(), anyString());
    }

    @Test
    @DisplayName("회원가입에 성공하면 계정을 생성하고,저장해야한다.")
    void join_ShouldCreateUser_WhenDataValid() throws Exception {
        // Given
        RequestJoin requestJoin = new RequestJoin("testUser", "password123", "password123", "Test User", true);

        doNothing().when(joinService).save(Mockito.any(RequestJoin.class), Mockito.any());

        // When & Then
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "userId": "testUser",
                            "password": "password123",
                            "confirmPassword": "password123",
                            "username": "Test User",
                            "agree": true
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));

        verify(joinService, times(1)).save(any(RequestJoin.class), any());
    }


}
