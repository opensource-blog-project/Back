package com.example.opensource_blog.domain.users;


import com.example.opensource_blog.dto.request.RequestJoin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.View;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final UserRepository userRepository;
    private final static String IdAndPasswordRegx = "^[a-zA-Z0-9]*$";
    private static Pattern pattern = Pattern.compile(IdAndPasswordRegx);
    private final View error;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestJoin form = (RequestJoin) target;

        /**
         * 0. 아이디 비밀번호는 4-8자리 숫자와 영어
         * 1. 아이디 중복 여부 체크
         * 2. 비밀번호 및 비밀번호 확인 일치 여부
         */
        String username = form.username();
        String password = form.password();
        String confirmPassword = form.confirmPassword();

        if(username!=null && !username.isBlank() && !pattern.matcher(username).matches()) {
            errors.rejectValue("username","username.invalid.format");
        }
        if(password!=null && !password.isBlank() && !pattern.matcher(password).matches()) {
            errors.rejectValue("password","password.invalid.format");
        }
        //1. 아이디 중복 여부 체크
        if(username!=null && !username.isBlank() && userRepository.existsByUsername(username)) {
            errors.rejectValue("username","Duplicate");
        }
        //2. 비밀번호 및 비밀번호 확인 일치 여부
        if(password!=null && !password.isBlank() && !confirmPassword.isBlank() && !password.equals(confirmPassword)) {
            errors.rejectValue("confirmPassword", "Mismatch");
        }

    }
}
