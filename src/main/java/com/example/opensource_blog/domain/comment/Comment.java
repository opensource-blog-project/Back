package com.example.opensource_blog.domain.comment;

import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserAccount user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    public Post post;

    @NotNull
    private String content;

    @Builder
    public Comment(int id, String content, UserAccount user, Post post) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.post = post;
    }

    // update
    public void update(String content) {
        this.content = content;
    }
}