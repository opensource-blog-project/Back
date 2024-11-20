package com.example.opensource_blog.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestLogin(

    @NotBlank
    String userId,

    @NotBlank
    String password
) {
}
