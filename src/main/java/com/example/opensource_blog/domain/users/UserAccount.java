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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserAccount of(String username, String password) {
        return new UserAccount(username, password);
    }

    public void updatePassword(String encodedPwd) {
        this.password = encodedPwd;
    }
}
