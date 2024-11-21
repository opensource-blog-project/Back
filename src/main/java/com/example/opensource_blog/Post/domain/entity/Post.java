package com.example.opensource_blog.Post.domain.entity;

import com.example.opensource_blog.domain.users.UserAccount;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @ManyToOne(fetch = FetchType.EAGER) // 기본값은 EAGER
    @JoinColumn(name = "user_id", nullable = false)  // 외래 키 매핑
    private UserAccount user;  // User 엔티티와 매핑

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String restaurant;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // 부모 참조
    private List<PostImages> images = new ArrayList<>();  // 빈 리스트로 초기화


}
