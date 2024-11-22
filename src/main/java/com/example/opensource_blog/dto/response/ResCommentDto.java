package com.example.opensource_blog.dto.response;

import com.example.opensource_blog.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResCommentDto {

    private int commentId;
    private String commentWriter;
    private String content;// 댓글 작성자

    @Builder
    public ResCommentDto(int commentId, String commentWriter, String content) {
        this.commentId = commentId;
        this.commentWriter = commentWriter;
        this.content = content;
    }

    public static ResCommentDto fromEntity(Comment comment) {
        return ResCommentDto.builder()
                .commentId(comment.getId())
                .commentWriter(comment.getUser().getUsername())
                .content(comment.getContent())
                .build();
    }
}