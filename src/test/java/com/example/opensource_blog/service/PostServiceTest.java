package com.example.opensource_blog.service;

import com.example.opensource_blog.domain.hashtag.*;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostImagesRepository;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.request.PostRequestDTO;
import com.example.opensource_blog.dto.response.PostListResponseDTO;
import com.example.opensource_blog.service.post.PostImageService;
import com.example.opensource_blog.service.post.PostService;
import com.example.opensource_blog.service.user.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private HashTagRepository hashTagRepository;

    @Mock
    private PostImagesRepository postImagesRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostImageService postImageService;

    @Mock
    private PostHashTagRepository postHashTagRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAllPosts 메서드를 호출하면 모든 게시물 리스트를 반환한다.")
    void getAllPosts_ShouldReturnPostList() {
        // Given
        Post post1 = new Post();
        post1.setPostId(1);
        post1.setTitle("Post 1");
        post1.setContent("Content 1");
        post1.setRestaurant("Restaurant 1");
        post1.setUser(new UserAccount("user1", "password", "User1"));

        Post post2 = new Post();
        post2.setPostId(2);
        post2.setTitle("Post 2");
        post2.setContent("Content 2");
        post2.setRestaurant("Restaurant 2");
        post2.setUser(new UserAccount("user2", "password", "User2"));

        when(postRepository.findAllWithUser()).thenReturn(List.of(post1, post2));

        // When
        List<PostListResponseDTO> posts = postService.getAllPosts();

        // Then
        assertEquals(2, posts.size());
        assertEquals("Post 1", posts.get(0).getTitle());
        assertEquals("Post 2", posts.get(1).getTitle());
    }

    @Test
    @DisplayName("createPost 메서드를 호출하면 게시글을 저장한다.")
    void createPost_ShouldSavePost() {
        // Given
        PostRequestDTO postRequestDTO = new PostRequestDTO();
        postRequestDTO.setTitle("New Post");
        postRequestDTO.setContent("New Content");
        postRequestDTO.setRestaurant("New Restaurant");
        postRequestDTO.setHashTagIds(List.of(1L, 2L));

        UserInfo userInfo = new UserInfo(new UserAccount("user1", "password", "User1"));
        Post post = postRequestDTO.toEntity(postRequestDTO);
        UserAccount user = new UserAccount("user1", "password", "User1");

        HashTag hashTag1 = HashTag.of("Tag1", HashTagType.CATEGORY);
        HashTag hashTag2 = HashTag.of("Tag2", HashTagType.UTILITY);

        when(userRepository.findByUserId(userInfo.getUsername())).thenReturn(Optional.of(user));
        when(hashTagRepository.findById(1L)).thenReturn(Optional.of(hashTag1));
        when(hashTagRepository.findById(2L)).thenReturn(Optional.of(hashTag2));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        // When
        Post savedPost = postService.createPost(postRequestDTO, List.of(), userInfo);

        // Then
        assertNotNull(savedPost);
        assertEquals("New Post", savedPost.getTitle());
        assertEquals("User1", savedPost.getUser().getUsername());
        verify(postHashTagRepository, times(2)).save(any(PostHashTag.class));
        verify(postImageService, times(1)).saveImages(anyList(), eq(savedPost));
    }

    @Test
    @DisplayName("updatePost 메서드를 호출하면 게시글을 업데이트한다.")
    void updatePost_ShouldUpdatePost() {
        // Given
        int postId = 1;
        PostRequestDTO postRequestDTO = new PostRequestDTO();
        postRequestDTO.setTitle("Updated Post");
        postRequestDTO.setContent("Updated Content");
        postRequestDTO.setRestaurant("Updated Restaurant");

        Post post = new Post();
        post.setPostId(postId);
        post.setTitle("Old Title");
        post.setContent("Old Content");
        post.setRestaurant("Old Restaurant");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // When
        Post updatedPost = postService.updatePost(postId, postRequestDTO, List.of(), new UserInfo(UserAccount.of("userId","password","username")));

        // Then
        assertEquals("Updated Post", updatedPost.getTitle());
        assertEquals("Updated Content", updatedPost.getContent());
        assertEquals("Updated Restaurant", updatedPost.getRestaurant());
        verify(postImageService, times(1)).updatePostImages(post, anyList());
    }

    @Test
    @DisplayName("deletePost 메서드를 호출하면 게시글을 삭제한다.")
    void deletePost_ShouldDeletePost() {
        // Given
        int postId = 1;
        Post post = new Post();
        post.setPostId(postId);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        doNothing().when(postImageService).deleteImagesByPost(post);
        doNothing().when(postRepository).deleteById(postId);

        // When
        postService.deletePost(postId);

        // Then
        verify(postImageService, times(1)).deleteImagesByPost(post);
        verify(postRepository, times(1)).deleteById(postId);
    }

}
