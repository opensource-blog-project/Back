package com.example.opensource_blog.execeptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CommonException{
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
