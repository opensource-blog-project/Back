package com.example.opensource_blog.Post.dto;


import com.example.opensource_blog.Post.domain.entity.Post;
import com.example.opensource_blog.domain.users.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private int postid;
    private String userid;
    private String title;
    private String content;
    private String restaurant;
    private List<String> imageUrls;

    // Post 엔티티를 인자로 받는 생성자 추가
    public PostDTO(Post post) {
        this.postid = post.getPostId();
        this.userid = post.getUser().getUserId();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.restaurant = post.getRestaurant();

    }
    // PostDTO를 Post 엔티티로 변환하는 메서드 추가
    public Post toEntity(UserAccount user) {
        Post post = new Post();
        post.setUser(user); // User 객체 설정
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setRestaurant(this.restaurant);
        return post;
    }
}
