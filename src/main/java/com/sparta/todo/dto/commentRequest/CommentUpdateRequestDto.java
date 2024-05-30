package com.sparta.todo.dto.commentRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {
    @NotNull(message = "todoId 는 필수 값입니다.")
    private Long todoId;
    @NotBlank(message = "content 는 필수 값입니다.")
    @Size(max = 30, message = "content 는 30자를 넘을 수 없습니다.")
    private String content;
    @NotNull(message = "id 는 필수 값입니다.")
    private Long id;
}
