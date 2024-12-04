package com.example.opensource_blog.service;

import com.example.opensource_blog.domain.users.JoinValidator;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.request.RequestJoin;
import com.example.opensource_blog.service.user.UserJoinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class UserJoinServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JoinValidator joinValidator;

    @Mock
    private Errors errors;

    @InjectMocks
    private UserJoinService userJoinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 객체 초기화
    }

    @Test
    @DisplayName("유효성검사 실패시에는 userRepository의 save메서드가 호출되면 안된다.")
    void save_ShouldNotSave_WhenValidationFails() {
        // Given
        RequestJoin requestJoin = new RequestJoin("testId", "password123", "password123", "username", true);

        // Mock 유효성 검사 실패
        doNothing().when(joinValidator).validate(requestJoin, errors);
        when(errors.hasErrors()).thenReturn(true);

        // When
        userJoinService.save(requestJoin, errors);

        // Then
        verify(userRepository, never()).save(any(UserAccount.class));
    }

    @Test
    @DisplayName("유효성검사 통과시 userRepository의 save메서드가 호출되야 한다.")
    void save_ShouldSave_WhenValidationPasses() {
        // Given
        RequestJoin requestJoin = new RequestJoin("testId", "password123", "password123", "username", true);

        // Mock 유효성 검사 통과
        doNothing().when(joinValidator).validate(requestJoin, errors);
        when(errors.hasErrors()).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // When
        userJoinService.save(requestJoin, errors);

        // Then
        verify(userRepository, times(1)).save(any(UserAccount.class));
    }
}
