package com.example.opensource_blog.service.user;

import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("{}: {}", getClass().getSimpleName(), "loadUserByUserId(String)");

        UserAccount userAccount = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        return new UserInfo(userAccount);
    }
}
