package com.example.opensource_blog.config;

import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.service.user.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Annotation;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UserInfo principal = new UserInfo(new UserAccount(annotation.userId(), "password", annotation.username()));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                principal,
                "password",
                principal.getAuthorities()
        );

        context.setAuthentication(auth);
        return context;
    }
}
