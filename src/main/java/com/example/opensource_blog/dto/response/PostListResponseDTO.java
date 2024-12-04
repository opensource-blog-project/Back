package com.example.opensource_blog.dto.response;

import com.example.opensource_blog.domain.hashtag.HashTagDto;
import com.example.opensource_blog.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class PostListResponseDTO {

    private int postId;
    private String title;
    private String postWriter;
    private String content;
    private String restaurant;
    private byte[] postImage;
    private List<HashTagDto> hashTags;

    @Builder
    public PostListResponseDTO(int postId, String title, String postWriter, String content, String restaurant, byte[] postImage, List<HashTagDto> hashTags) {
        this.postId = postId;
        this.title = title;
        this.postWriter = postWriter;
        this.content = content;
        this.restaurant = restaurant;
        this.postImage = postImage;
        this.hashTags = hashTags;
    }

    // Entity -> DTO
    public static PostListResponseDTO fromEntity(Post post) {
        log.info("Mapping Post entity to DTO: postId={}, title={}, postWriter={}",
                post.getPostId(), post.getTitle(), post.getUser().getUsername());
        return PostListResponseDTO.builder()
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