package com.example.opensource_blog.domain.post;

import com.example.opensource_blog.domain.hashtag.PostHashTag;
import com.example.opensource_blog.domain.likes.Like;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.comment.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @ManyToOne(fetch = FetchType.EAGER) // 기본값은 EAGER
    @JoinColumn(name = "user_id", nullable = false)  // 외래 키 매핑
    private UserAccount user;  // User 엔티티와 매핑


    @Column(nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Column(nullable = false)
    private String restaurant;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // 부모 참조
    private List<PostImages> images = new ArrayList<>();  // 빈 리스트로 초기화

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    public List<Comment> comments = new ArrayList<>();


    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PostHashTag> postHashTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Like> likes = new ArrayList<>();

//    private boolean saved;


//    public void setSaved(boolean saved) {
//        this.saved = saved;
//    }


}
