package com.example.opensource_blog.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record RequestJoin(

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String confirmPassword,

        @AssertTrue
        boolean agree
) { }
