package com.example.opensource_blog.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class UserAccountInfo {
    private String user_id;
    private String password;
    private String username;

    public UserAccountInfo(String user_id, String password, String username) {
        this.user_id = user_id;
        this.password = password;
        this.username = username;
    }
}
