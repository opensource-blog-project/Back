package com.example.opensource_blog.dto.request;

import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.users.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDTO {

    private String title;
    private String content;
    private String restaurant;

    // PostRequestDTO를 Post 엔티티로 변환하는 메서드
    public Post toEntity(PostRequestDTO requestDTO) {
        Post post = new Post();
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setRestaurant(this.restaurant);
        return post;
    }
}
