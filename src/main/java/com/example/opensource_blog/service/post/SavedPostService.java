package com.example.opensource_blog.service.post;

import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.post.SavePost;
import com.example.opensource_blog.domain.post.SavedPostRepository;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedPostService {
    private final SavedPostRepository savedPostRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void savePost(String userId, Integer postId) {
        UserAccount user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 이미 저장된 게시글인지 확인
        if (savedPostRepository.existsByUserAndPost(user, post)) {
            throw new RuntimeException("Post is already saved");
        }

        // 저장
        SavePost savedPost = new SavePost();
        savedPost.setUser(user);
        savedPost.setPost(post);
        savedPostRepository.save(savedPost);
    }

    @Transactional
    public void removeSavedPost(String userId, Integer postId) {
        UserAccount user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        savedPostRepository.deleteByPostIdAndUsername(userId, postId);
    }
}
