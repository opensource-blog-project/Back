package com.example.opensource_blog.domain.hashtag;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Table(name = "Hash_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HashTagType hashTagType;

    @OneToMany(mappedBy = "hashTag",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PostHashTag> postHashTags = new ArrayList<>();

    public static HashTag of(String name, HashTagType hashTagType) {
        HashTag hashTag = new HashTag();
        hashTag.name = name;
        hashTag.hashTagType = hashTagType;
        return hashTag;
    }
}
