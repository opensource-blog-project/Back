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
    private String content;
    private String commentWriter; // 댓글 작성자

    @Builder
    public ResCommentDto(int commentId, String content, String commentWriter) {
        this.commentId = commentId;
        this.content = content;
        this.commentWriter = commentWriter;
    }

    public static ResCommentDto fromEntity(Comment comment) {
        return ResCommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .commentWriter(comment.getUser().getUserId())
                .build();
    }
}