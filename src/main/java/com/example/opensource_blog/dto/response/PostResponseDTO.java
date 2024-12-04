package com.example.opensource_blog.dto.response;

import com.example.opensource_blog.domain.hashtag.HashTagDto;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostImages;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {

    private int postId;
    private String title;
    private String postWriter;
    private String content;
    private String restaurant;
    private List<byte[]> postImages;
    private List<HashTagDto> hashTags;

    @Builder
    public PostResponseDTO(int postId, String title, String postWriter, String content, String restaurant, List<byte[]> postImages, List<HashTagDto> hashTags) {
        this.postId = postId;
        this.title = title;
        this.postWriter = postWriter;
        this.content = content;
        this.restaurant = restaurant;
        this.postImages = postImages;
        this.hashTags = hashTags;
    }

    // Entity -> DTO
    public static PostResponseDTO fromEntity(Post post) {

        return PostResponseDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .postWriter(post.getUser().getUsername())
                .content(post.getContent())
                .restaurant(post.getRestaurant())
                .postImages(post.getImages() != null
                        ? post.getImages().stream().map(PostImages::getImageData).collect(Collectors.toList())
                        : null)
                .hashTags(HashTagDto.fromPostHashTags(post.getPostHashTags()))
                .build();
    }

}