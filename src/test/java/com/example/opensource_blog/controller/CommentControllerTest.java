package com.example.opensource_blog.controller;


import com.example.opensource_blog.config.SecurityTestConfig;
import com.example.opensource_blog.config.WithMockCustomUser;
import com.example.opensource_blog.domain.comment.Comment;
import com.example.opensource_blog.domain.comment.CommentRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.request.ReqCommentDto;
import com.example.opensource_blog.dto.response.ResCommentDto;
import com.example.opensource_blog.jwt.TokenProvider;
import com.example.opensource_blog.service.comment.CommentService;
import com.example.opensource_blog.service.user.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@Import(SecurityTestConfig.class) // Security 관련 설정 임포트
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CommentRepository commentRepository;


    @MockBean
    private CommentService commentService;
    @MockBean
    private TokenProvider tokenProvider;

    @BeforeEach
    void setUp() {
        Mockito.reset(commentService);
    }

    @Test
    @WithMockUser(username = "testUser")
    @DisplayName("getAllComments 메서드를 호출하면 댓글 리스트를 반환한다.")
    void getAllComments_ShouldReturnCommentList() throws Exception {
        // Given
        int postId = 1;
        List<ResCommentDto> mockComments = List.of(
                new ResCommentDto(1, "user1", "This is a comment"),
                new ResCommentDto(2, "user2", "Another comment")
        );

        when(commentService.getAllComments(postId)).thenReturn(mockComments);

        // When & Then
        mockMvc.perform(get("/posts/{postId}/comment/list", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].commentId").value(1))
                .andExpect(jsonPath("$[0].commentWriter").value("user1"))
                .andExpect(jsonPath("$[0].content").value("This is a comment"));

        verify(commentService, times(1)).getAllComments(postId);
    }

    @Test
    @WithMockCustomUser(username = "testUser", userId = "userId")
    @DisplayName("createComment 메서드로 댓글을 성공적으로 생성하면 201 상태코드와 ResCommentDto를 반환한다.")
    void createComment_ShouldReturnCreatedComment() throws Exception {
        // Given
        int postId = 1;
        ReqCommentDto reqCommentDto = new ReqCommentDto("New comment");
        ResCommentDto resCommentDto = new ResCommentDto(1, "testUser", "New comment");

        when(commentService.create(eq(postId), any(UserInfo.class), any(ReqCommentDto.class))).thenReturn(resCommentDto);

        // When & Then
        mockMvc.perform(post("/posts/{postId}/comment/create", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "content": "New comment"
                            }
                            """))
                .andDo(print()) // 디버깅용
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.commentId").value(1))
                .andExpect(jsonPath("$.content").value("New comment"));
    }


    @Test
    @WithMockCustomUser(username = "testUser", userId = "userId")
    @DisplayName("updateComment 메서드를 호출하면 내용이 변경된 댓글을 반환한다.")
    void updateComment_ShouldReturnUpdatedComment() throws Exception {
        // Given
        int commentId = 1;
        ReqCommentDto reqCommentDto = new ReqCommentDto("Updated comment");
        ResCommentDto resCommentDto = new ResCommentDto(1, "testUser", "Updated comment");

        when(commentService.update(eq(commentId), any(ReqCommentDto.class))).thenReturn(resCommentDto);

        // When & Then
        mockMvc.perform(put("/posts/{postId}/comment/{commentId}/update", 1, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "content": "Updated comment"
                            }
                            """))
                .andDo(print()) // 응답 출력
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(1))
                .andExpect(jsonPath("$.content").value("Updated comment"));

        verify(commentService, times(1)).update(eq(commentId), any(ReqCommentDto.class));
    }


}
