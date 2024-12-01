package com.example.opensource_blog.dto.response;

import com.example.opensource_blog.domain.hashtag.HashTagDto;
import com.example.opensource_blog.domain.post.Post;
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
    private byte[] postImage;
    private List<HashTagDto> hashTags;

    @Builder
    public PostResponseDTO(int postId, String title, String postWriter, String content, String restaurant, byte[] postImage, List<HashTagDto> hashTags) {
        this.postId = postId;
        this.title = title;
        this.postWriter = postWriter;
        this.content = content;
        this.restaurant = restaurant;
        this.postImage = postImage;
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
                .postImage(post.getImages() != null
                        ? post.getImages().getFirst().getImageData() // 첫 번째 이미지의 바이트 데이터 가져오기
                        : null)
                .hashTags(HashTagDto.fromPostHashTags(post.getPostHashTags()))
                .build();
    }

}