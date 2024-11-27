package com.example.opensource_blog.service.user;

import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.response.ResponseLogin;
import com.example.opensource_blog.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;


    public ResponseLogin authenticate(String userId,String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

        Authentication authentication =authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String username = getUsername(userId);

        // 인증 정보를 가지고 JWT AccessToken 발급
        String accessToken = tokenProvider.createToken(authentication);
        return ResponseLogin.builder()
                .accessToken(accessToken)
                .username(username)
                .build();
    }
    public String getUsername(String userId) {
        UserAccount userAccount = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("user not found" + userId));
        return userAccount.getUsername();

    }
}
