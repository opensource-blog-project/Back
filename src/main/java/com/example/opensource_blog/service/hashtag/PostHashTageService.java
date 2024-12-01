package com.example.opensource_blog.service.hashtag;

import com.example.opensource_blog.domain.hashtag.HashTagRepository;
import com.example.opensource_blog.domain.hashtag.PostHashTag;
import com.example.opensource_blog.domain.hashtag.PostHashTagRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.dto.response.PostListResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostHashTageService {
    private final HashTagRepository hashTagRepository;
    private final PostHashTagRepository postHashTagRepository;
    private final PostRepository postRepository;


    @Transactional
    public List<PostListResponseDTO> getPostsByHashTag(Long hashTagId) {
        List<Post> posts = postHashTagRepository.findPostsByHashTagId(hashTagId);
        List<PostListResponseDTO> postList = posts.stream()
                .map(PostListResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return postList;
    }

    public List<PostHashTag> getPostHashTagFromPost(Post post) {
        List<PostHashTag> postHashTags = postHashTagRepository.findByPost(post);
        return postHashTags;
    }

}
