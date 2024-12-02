package com.example.opensource_blog.domain;


import com.example.opensource_blog.domain.users.JoinValidator;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.request.RequestJoin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class JoinValidatorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Errors errors;

    private JoinValidator joinValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        joinValidator = new JoinValidator(userRepository, null);
    }

    @Test
    @DisplayName("유저아이디 형식이 올바르지 않는 경우 예외가 발생한다.")
    void validate_ShouldAddError_WhenUserIdFormatInvalid() {
        // Given
        RequestJoin requestJoin = new RequestJoin("invalid id!", "password123", "password123", "username", true);

        // When
        joinValidator.validate(requestJoin, errors);

        // Then
        verify(errors, times(1)).rejectValue("userId", "userId.invalid.format");
    }

    @Test
    @DisplayName("이미 동일한 아이디가 존재하는 경우 예외가 발생한다.")
    void validate_ShouldAddError_WhenUserIdDuplicate() {
        // Given
        RequestJoin requestJoin = new RequestJoin("testId", "password123", "password123", "username", true);
        when(userRepository.existsByUserId("testId")).thenReturn(true);

        // When
        joinValidator.validate(requestJoin, errors);

        // Then
        verify(errors, times(1)).rejectValue("userId", "Duplicate");
    }

    @Test
    @DisplayName("비밀번호와 확인 비밀번호가 일치하지 않는 경우 예외가 발생한다.")
    void validate_ShouldAddError_WhenPasswordAndConfirmMismatch() {
        // Given
        RequestJoin requestJoin = new RequestJoin("testId", "password123", "wrongPassword", "username", true);

        // When
        joinValidator.validate(requestJoin, errors);

        // Then
        verify(errors, times(1)).rejectValue("confirmPassword", "Mismatch");
    }

}
