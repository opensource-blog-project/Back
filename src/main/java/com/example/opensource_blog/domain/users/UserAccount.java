package com.example.opensource_blog.domain.users;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserAccount {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    public UserAccount(String userId, String password, String username) {
        this.userId = userId;
        this.password = password;
        this.username = username;
    }

    public static UserAccount of(String userId, String password, String username) {
        return new UserAccount(userId,password,username);
    }

    public void updatePassword(String encodedPwd) {
        this.password = encodedPwd;
    }
}
