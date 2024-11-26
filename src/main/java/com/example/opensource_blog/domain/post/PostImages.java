package com.example.opensource_blog.domain.post;

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
    private Long imageId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference  // 자식 참조
    private Post post;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;  // BLOB 데이터 저장

    @Override
    public String toString() {
        return "PostImages{" +
                "image_id=" + imageId +
                ", image_url='" + imageUrl + '\'' +
                ", image_data=" + Arrays.toString(imageData) +
                '}';
    }
}
