package com.example.opensource_blog.Post.dto.response;

import com.example.opensource_blog.Post.domain.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {

    private int postid;
    private String userid;
    private String title;
    private String content;
    private String restaurant;
    private List<String> imageUrls;

    // Post 엔티티를 PostResponseDTO로 변환하는 생성자
    public PostResponseDTO(Post post) {
        this.postid = post.getPostId();
        this.userid = post.getUser().getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.restaurant = post.getRestaurant();
        // 이미지 URL 리스트 변환
        this.imageUrls = post.getImages() != null
                ? post.getImages().stream().map(image -> image.getImageurl()).collect(Collectors.toList())
                : null;
    }
}