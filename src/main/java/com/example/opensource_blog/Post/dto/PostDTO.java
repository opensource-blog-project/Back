package com.example.opensource_blog.Post.dto;


import com.example.opensource_blog.Post.domain.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private String userid;
    private String title;
    private String content;
    private String restaurant;
    private List<String> imageUrls;

    // Post 엔티티를 인자로 받는 생성자 추가
    public Post toEntity() {
        Post post = new Post();
        post.setUserid(this.userid);
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setRestaurant(this.restaurant);

        return post;
    }
}
