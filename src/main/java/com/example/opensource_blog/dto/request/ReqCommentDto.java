package com.example.opensource_blog.dto.request;

import com.example.opensource_blog.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqCommentDto {

    private String content;

    @Builder
    public ReqCommentDto(String content) {
        this.content = content;
    }

    public static Comment toEntity(ReqCommentDto dto) {
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }
}