package com.example.opensource_blog.domain.post;

import com.example.opensource_blog.domain.users.UserAccount;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId", columnDefinition = "int")
    private Integer postId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserAccount user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Post(UserAccount user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public static Post of(UserAccount user, String title, String content) {
        return new Post(user, title, content);
    }
}