package com.sparta.todo.dto.commentRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentDeleteRequestDto {
    @NotNull(message = "Todo ID는 필수 값입니다.")
    private Long todoId;
    @NotNull(message = "Comment ID는 필수 값입니다.")
    private Long id;
}
