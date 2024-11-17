package com.example.opensource_blog.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Table(name = "postimages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PostImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postId", referencedColumnName = "postId", nullable = false)
    private Post post;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public PostImages(Post post, String imageUrl) {
        this.post = post;
        this.imageUrl = imageUrl;
    }

    public static PostImages of(Post post, String imageUrl) {
        return new PostImages(post, imageUrl);
    }
}
