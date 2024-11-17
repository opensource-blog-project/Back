package com.example.opensource_blog.controller.commons;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class JSONData<T> {

    private boolean success = true;

    @NonNull
    private T data;

    private String message;
    private HttpStatus status = HttpStatus.OK;
}
