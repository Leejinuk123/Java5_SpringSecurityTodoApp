package com.sparta.todo.dto.commentRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {
    @NotNull
    private Long todoId;
    @NotNull
    private Long id;
    @NotBlank
    @Size(max = 30)
    private String content;
}
