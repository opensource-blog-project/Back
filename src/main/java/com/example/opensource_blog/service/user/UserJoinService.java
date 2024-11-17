package com.example.opensource_blog.service.user;

import com.example.opensource_blog.domain.users.JoinValidator;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.request.RequestJoin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Service
public class UserJoinService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JoinValidator joinValidator;

    public void save(RequestJoin join, Errors errors) {
        joinValidator.validate(join, errors);
        if (errors.hasErrors()) {
            return;
        }
        save(join);
    }

    public void save(RequestJoin join) {
        String encodedPassword = passwordEncoder.encode(join.password());
        UserAccount userAccount = new UserAccount(join.username(), encodedPassword);
        userRepository.save(userAccount);
    }

}
