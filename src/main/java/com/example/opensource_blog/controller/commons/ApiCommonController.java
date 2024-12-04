package com.example.opensource_blog.controller.commons;

import com.example.opensource_blog.execeptions.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiCommonController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONData<Object>> errorHandler(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException)e;
            status = commonException.getStatus();
        } else if (e instanceof BadCredentialsException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (e instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
        }else if (e instanceof IllegalArgumentException) {
            status = HttpStatus.NOT_FOUND;
        }

        JSONData<Object> data = new JSONData<>();
        data.setSuccess(false);
        data.setMessage(e.getMessage());
        data.setStatus(status);

        e.printStackTrace();

        return ResponseEntity.status(status).body(data);
    }
}
