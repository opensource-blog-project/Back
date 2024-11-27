package com.example.opensource_blog.domain.hashtag;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Builder
public class HashTagDto {
    private Long hashTagId;
    private HashTagType hashTagType;
    private String name;

    public HashTagDto(Long hashTagId, HashTagType hashTagType, String name) {
        this.hashTagId = hashTagId;
        this.hashTagType = hashTagType;
        this.name = name;
    }
    public static HashTagDto fromEntity(HashTag hashTag) {
        return HashTagDto.builder()
                .hashTagId(hashTag.getId())
                .hashTagType(hashTag.getHashTagType())
                .name(hashTag.getName())
                .build();
    }
    public static List<HashTagDto> fromPostHashTags(List<PostHashTag> postHashTags) {
        return postHashTags.stream().map(postHashTag -> {
            HashTag hashTag = postHashTag.getHashTag();
            return fromEntity(hashTag);
        }).collect(Collectors.toList());

    }
}
