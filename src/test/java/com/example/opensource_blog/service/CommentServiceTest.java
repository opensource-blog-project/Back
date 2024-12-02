package com.example.opensource_blog.service;


import com.example.opensource_blog.domain.comment.Comment;
import com.example.opensource_blog.domain.comment.CommentRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.request.ReqCommentDto;
import com.example.opensource_blog.dto.response.ResCommentDto;
import com.example.opensource_blog.service.comment.CommentService;
import com.example.opensource_blog.service.user.UserInfo;
import jakarta.persistence.EntityNotFoundException;
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

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("게시글이 존재할 경우에 getAllComments 메서드를 호출하면 댓글 리스트를 반환해야한다.")
    void getAllComments_ShouldReturnComments_WhenPostExists() {
        // Given
        int postId = 1;
        Comment comment1 = new Comment(1, "Comment 1", new UserAccount("user1", "password", "User1"), new Post());
        Comment comment2 = new Comment(2, "Comment 2", new UserAccount("user2", "password", "User2"), new Post());
        when(commentRepository.findAllByPostIdWithUserAndPost(postId)).thenReturn(List.of(comment1, comment2));

        // When
        List<ResCommentDto> comments = commentService.getAllComments(postId);

        // Then
        assertEquals(2, comments.size());
        assertEquals("User1", comments.get(0).getCommentWriter());
        assertEquals("User2", comments.get(1).getCommentWriter());
    }

    @Test
    @DisplayName("게시글과 유저가 존재할경우에 create 메서드를 호출하면 댓글을 생성한다.")
    void create_ShouldSaveComment_WhenPostAndUserExist() {
        // Given
        int postId = 1;
        UserInfo userInfo = new UserInfo(new UserAccount("user1", "password", "User1"));
        ReqCommentDto reqCommentDto = new ReqCommentDto("New Comment");
        Post post = new Post();
        UserAccount userAccount = new UserAccount("user1", "password", "User1");
        Comment comment = new Comment(1, reqCommentDto.getContent(), userAccount, post);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findByUserId(userInfo.getUsername())).thenReturn(Optional.of(userAccount));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // When
        ResCommentDto savedComment = commentService.create(postId, userInfo, reqCommentDto);

        // Then
        assertEquals("User1", savedComment.getCommentWriter());
        assertEquals("New Comment", savedComment.getContent());
    }

    @Test
    @DisplayName("게시글이 존재하지 않을 경우에는 예외가 발생한다.")
    void create_ShouldThrowException_WhenPostDoesNotExist() {
        // Given
        int postId = 1;
        UserInfo userInfo = new UserInfo(new UserAccount("user1", "password", "User1"));
        ReqCommentDto reqCommentDto = new ReqCommentDto("New Comment");

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(EntityNotFoundException.class, () -> commentService.create(postId, userInfo, reqCommentDto));
        assertEquals("Post with id 1 not found", exception.getMessage());
    }

    @Test
    @DisplayName("댓글이 존재할때 update 메서드를 호출하면 댓글의 내용이 변경된다.")
    void update_ShouldUpdateComment_WhenCommentExists() {
        // Given
        int commentId = 1;
        ReqCommentDto reqCommentDto = new ReqCommentDto("Updated Comment");
        Comment comment = new Comment(commentId, "Old Comment", new UserAccount("userId","password","username"), new Post());
        when(commentRepository.findByIdWithUserAndPost(commentId)).thenReturn(Optional.of(comment));

        // When
        ResCommentDto updatedComment = commentService.update(commentId, reqCommentDto);

        // Then
        assertEquals("Updated Comment", updatedComment.getContent());
    }

    @Test
    @DisplayName("commentId에 대응하는 댓글이 존재하지 않는다면 예외가 발생한다.")
    void update_ShouldThrowException_WhenCommentDoesNotExist() {
        // Given
        int commentId = 1;
        ReqCommentDto reqCommentDto = new ReqCommentDto("Updated Comment");

        when(commentRepository.findByIdWithUserAndPost(commentId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(EntityNotFoundException.class, () -> commentService.update(commentId, reqCommentDto));
        assertEquals("Comment with id 1 not found", exception.getMessage());
    }

    @Test
    @DisplayName("댓글이 존재할 경우에 delete 메서드를 호출하면 댓글이 삭제된다.")
    void delete_ShouldRemoveComment_WhenCommentExists() {
        // Given
        int commentId = 1;

        doNothing().when(commentRepository).deleteById(commentId);

        // When
        commentService.delete(commentId);

        // Then
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
