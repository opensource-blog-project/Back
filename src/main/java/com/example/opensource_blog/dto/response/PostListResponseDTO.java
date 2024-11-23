package com.example.opensource_blog.dto.response;

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
public class PostListResponseDTO {

    private int postId;
    private String title;
    private String postWriter;
    private String content;
    private String restaurant;
    private byte[] postImage;

    @Builder
    public PostListResponseDTO(int postId, String title, String postWriter, String content,String restaurant, byte[] postImage) {
        this.postId = postId;
        this.title = title;
        this.postWriter = postWriter;
        this.content = content;
        this.restaurant = restaurant;
        this.postImage = postImage;
    }



    // Entity -> DTO
    public static PostListResponseDTO fromEntity(Post post) {

        return PostListResponseDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .postWriter(post.getUser().getUsername())
                .content(post.getContent())
                .restaurant(post.getRestaurant())
                .postImage(post.getImages() != null
                        ? post.getImages().getFirst().getImageData() // 첫 번째 이미지의 바이트 데이터 가져오기
                        : null)
                .build();
    }
}