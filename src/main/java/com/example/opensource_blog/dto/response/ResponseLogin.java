package com.example.opensource_blog.dto.response;

import lombok.Builder;

@Builder
public record ResponseLogin(
        String accessToken
) {
}
