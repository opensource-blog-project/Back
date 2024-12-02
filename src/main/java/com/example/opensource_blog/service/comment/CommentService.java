package com.example.opensource_blog.service.comment;

import com.example.opensource_blog.domain.comment.Comment;
import com.example.opensource_blog.domain.comment.CommentRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.dto.request.ReqCommentDto;
import com.example.opensource_blog.dto.response.ResCommentDto;
import com.example.opensource_blog.service.user.UserInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<ResCommentDto> getAllComments(int postId) {
        List<Comment> comments = commentRepository.findAllByPostIdWithUserAndPost(postId);
        List<ResCommentDto> commentList = comments.stream()
                .map(ResCommentDto::fromEntity)
                .collect(Collectors.toList());
        return commentList;
    }

    public ResCommentDto create(int postId, UserInfo userInfo, ReqCommentDto reqCommentDto) {
        // post 정보 검색
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("Post with id " + postId + " not found")
        );
        // user 정보 검색
        String userId = userInfo.getUsername();
        UserAccount user = userRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userId + " not found")
        );
        // Entity 변환, 연관관계 매핑
        Comment comment = ReqCommentDto.toEntity(reqCommentDto);
        comment.setPost(post);
        comment.setUser(user);

        Comment saveComment = commentRepository.save(comment);
        return ResCommentDto.fromEntity(saveComment);
    }

    public ResCommentDto update(int commentId, ReqCommentDto reqCommentDto) {
        Comment comment = commentRepository.findByIdWithUserAndPost(commentId).orElseThrow(
                () -> new EntityNotFoundException("Comment with id " + commentId + " not found")
        );
        comment.update(reqCommentDto.getContent());
        return ResCommentDto.fromEntity(comment);
    }

    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
    }

    public Page<ResCommentDto> getCommentsByUser(String userId, Pageable pageable) {
        return commentRepository.findByUserUsername(userId, pageable)
                .map(ResCommentDto::fromEntity);
    }
}