package com.example.opensource_blog.domain.hashtag;

import com.example.opensource_blog.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Table(name = "Post_Hash_Tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PostHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_Id",nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "hashtag_Id",nullable = false)
    private HashTag hashTag;

    public static PostHashTag of(Post post,HashTag hashTag) {
        PostHashTag postHashTag = new PostHashTag();
        postHashTag.post = post;
        postHashTag.hashTag = hashTag;
        return postHashTag;
    }
}
