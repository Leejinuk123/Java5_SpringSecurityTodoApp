package com.sparta.todo.dto.commentResponse;

import com.sparta.todo.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String userName;
    private String content;
    private LocalDateTime createAt;

    public CommentResponseDto(Comment saveComment) {
        this.userName = saveComment.getUserName();
        this.content = saveComment.getContent();
        this.createAt = saveComment.getCreatedAt();
    }
}
