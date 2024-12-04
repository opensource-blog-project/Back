package com.example.opensource_blog.service;


import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.jwt.TokenProvider;
import com.example.opensource_blog.service.user.UserLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserLoginServiceTest {

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserLoginService userLoginService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("인증에 성공하면 Response 객체를 반환한다.")
    void authenticate_ShouldReturnResponseLogin_WhenAuthenticationSucceeds() {
        // Given
        String userId = "testUser";
        String password = "password123";
        String username = "testUsername";

        when(authenticationManagerBuilder.getObject()).thenReturn(authenticationManager);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(new UserAccount(userId, password, username)));
        when(tokenProvider.createToken(authentication)).thenReturn("testAccessToken");

        // When
        var responseLogin = userLoginService.authenticate(userId, password);

        // Then
        assertEquals("testAccessToken", responseLogin.accessToken());
        assertEquals("testUsername", responseLogin.username());
    }

    @Test
    @DisplayName("아이디가 존재하지 않는다면 예외가 발생한다.")
    void authenticate_ShouldThrowException_WhenUserNotFound() {
        // Given
        String userId = "nonExistingUser";
        String password = "password123";

        when(userRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(NullPointerException.class, () -> userLoginService.authenticate(userId, password));

    }

    @Test
    @DisplayName("아이디에 대응하는 유저정보가 존재할 경우에는 이름을 반환해야한다.")
    void getUsername_ShouldReturnUsername_WhenUserExists() {
        // Given
        String userId = "testUser";
        String username = "testUsername";

        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(new UserAccount(userId, "password123", username)));

        // When
        String result = userLoginService.getUsername(userId);

        // Then
        assertEquals("testUsername", result);
    }

}
