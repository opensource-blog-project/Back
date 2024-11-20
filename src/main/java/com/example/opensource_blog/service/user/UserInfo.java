package com.example.opensource_blog.service.user;

import com.example.opensource_blog.domain.users.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserInfo implements UserDetails {

    private String userId;
    private String password;
    private String username;


    public UserInfo(UserAccount entity) {
        userId = entity.getUserId();
        password = entity.getPassword();
        username = entity.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

}
