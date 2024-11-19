package com.example.opensource_blog.Post.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Entity
@Table(name = "postimages")  // 데이터베이스의 실제 테이블명과 맞춤
@Getter @Setter
public class PostImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postid", nullable = false)
    @JsonBackReference  // 자식 참조
    private Post post;

    @Column(nullable = false)
    private String imageurl;

    @Lob
    @Column(name = "imagedata", nullable = false)
    private byte[] imagedata;  // BLOB 데이터 저장

    @Override
    public String toString() {
        return "PostImages{" +
                "imageid=" + imageid +
                ", imageurl='" + imageurl + '\'' +
                ", imagedata=" + Arrays.toString(imagedata) +
                '}';
    }
}
