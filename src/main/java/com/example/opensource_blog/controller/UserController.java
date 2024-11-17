package com.example.opensource_blog.controller;


import com.example.opensource_blog.controller.commons.JSONData;
import com.example.opensource_blog.dto.request.RequestJoin;
import com.example.opensource_blog.dto.request.RequestLogin;
import com.example.opensource_blog.dto.response.ResponseLogin;
import com.example.opensource_blog.execeptions.BadRequestException;
import com.example.opensource_blog.jwt.CustomJwtFilter;
import com.example.opensource_blog.service.user.UserInfoService;
import com.example.opensource_blog.service.user.UserJoinService;
import com.example.opensource_blog.service.user.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserLoginService loginService;
    private final UserInfoService infoService;
    private final UserJoinService joinService;

    /**
     * accessToken 발급
     *
     */
    @PostMapping("/token")
    public ResponseEntity<JSONData<ResponseLogin>> authorize(@Valid @RequestBody RequestLogin requestLogin, Errors errors) {

        // 유효성 검사 처리
        errorProcess(errors);
        ResponseLogin token = loginService.authenticate(requestLogin.username(),requestLogin.password());

        HttpHeaders headers = new HttpHeaders();
        headers.add(CustomJwtFilter.AUTHORIZATION_HEADER,"Bearer "+token.accessToken());
        JSONData<ResponseLogin> data = new JSONData<>(token);
        return ResponseEntity.status(data.getStatus())
                .headers(headers)
                .body(data);
    }

    /**
     * 회원가입 처리
     *
     * @return
     */
    @PostMapping
    public ResponseEntity<JSONData<Object>> join(@RequestBody @Valid RequestJoin form, Errors errors) {

        joinService.save(form, errors);

        // 유효성 검사 처리
        errorProcess(errors);

        HttpStatus status = HttpStatus.CREATED;
        JSONData<Object> data = new JSONData<>();
        data.setSuccess(true);
        data.setStatus(status);

        return ResponseEntity.status(status).body(data);
    }

    private void errorProcess(Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.toString());
        }
    }

}
