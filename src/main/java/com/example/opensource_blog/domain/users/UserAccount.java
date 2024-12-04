package com.example.opensource_blog.domain.users;


import com.example.opensource_blog.domain.comment.Comment;
import com.example.opensource_blog.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Comment> comments = new ArrayList<>();

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
