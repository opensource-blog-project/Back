package com.example.opensource_blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestLike(
        @NotNull
        Integer postId,

        @NotBlank
        String username
) {
}
