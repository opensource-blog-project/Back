package com.example.opensource_blog.domain.likes;

import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.users.UserAccount;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Table(name = "likes", uniqueConstraints = @UniqueConstraint(columnNames = {"postId", "user_id"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postId", referencedColumnName = "postId", nullable = false)
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserAccount user;

    public Like(Post post, UserAccount user) {
        this.post = post;
        this.user = user;
    }

    public static Like of(Post post, UserAccount user) {
        return new Like(post, user);
    }
}