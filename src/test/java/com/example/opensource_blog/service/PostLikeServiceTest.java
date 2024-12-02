package com.example.opensource_blog.service;

import com.example.opensource_blog.domain.likes.Like;
import com.example.opensource_blog.domain.likes.LikeRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.service.likes.PostLikeService;
import com.example.opensource_blog.service.user.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostLikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostLikeService postLikeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 객체 초기화
    }

    @Test
    @DisplayName("유저가 게시글에 아직 좋아요를 누르지 않은 상황에서 좋아요를 누르면 likeRepository에 save메서드가 호출된다.")
    void pushLike_ShouldAddLike_WhenNotAlreadyLiked() {
        // Given
        int postId = 1;
        UserAccount userAccount = UserAccount.of("testUserId", "password123", "testUser");
        UserInfo userInfo = new UserInfo(userAccount);
        Post post = new Post();
        post.setPostId(postId);


        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findByUserId(userInfo.getUsername())).thenReturn(Optional.of(userAccount));
        when(likeRepository.findByPostAndUser(post, userAccount)).thenReturn(Optional.empty());

        // When
        postLikeService.pushLike(postId, userInfo);

        // Then
        verify(likeRepository, times(1)).save(any(Like.class));
        verify(likeRepository, never()).delete(any(Like.class));
    }

    @Test
    @DisplayName("유저가 게시글에 이전에 좋아요를 눌른 상황에서 좋아요를 누르면 likeRepository에 delete메서드가 호출된다.")
    void pushLike_ShouldRemoveLike_WhenAlreadyLiked() {
        // Given
        int postId = 1;
        UserInfo userInfo = new UserInfo(new UserAccount("testUserId", "password123", "testUser"));
        Post post = new Post();
        post.setPostId(postId);

        UserAccount userAccount = UserAccount.of("testUserId", "password123", "testUser");
        Like like = Like.of(post, userAccount);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findByUserId(userInfo.getUsername())).thenReturn(Optional.of(userAccount));
        when(likeRepository.findByPostAndUser(post, userAccount)).thenReturn(Optional.of(like));

        // When
        postLikeService.pushLike(postId, userInfo);

        // Then
        verify(likeRepository, times(1)).delete(like);
        verify(likeRepository, never()).save(any(Like.class));
    }

    @Test
    @DisplayName("getLikeCountByPostId 메서드를 호출하면 현재 좋아요수를 반환한다.")
    void getLikeCountByPostId_ShouldReturnCount_WhenPostExists() {
        // Given
        int postId = 1;
        int likeCount = 5;

        when(postRepository.existsById(postId)).thenReturn(true);
        when(likeRepository.countByPost_id(postId)).thenReturn(likeCount);

        // When
        int result = postLikeService.getLikeCountByPostId(postId);

        // Then
        assertEquals(likeCount, result);
    }

    @Test
    @DisplayName("postId에 대응하는 게시글이 존재하지 않는다면 예외가 발생한다.")
    void getLikeCountByPostId_ShouldThrowException_WhenPostDoesNotExist() {
        // Given
        int postId = 1;

        when(postRepository.existsById(postId)).thenReturn(false);

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postLikeService.getLikeCountByPostId(postId)
        );
        assertEquals("post not founded" + postId, exception.getMessage());
    }
}
