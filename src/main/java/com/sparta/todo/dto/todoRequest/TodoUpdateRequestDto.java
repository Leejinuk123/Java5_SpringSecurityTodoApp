package com.sparta.todo.dto.todoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class TodoUpdateRequestDto {
    @Size(max = 50, message = "일정의 제목은 50자를 넘을 수 없습니다.")
    @NotBlank(message = "일정의 제목은 필수입니다.")
    private String title;
    @Size(max = 100, message = "일정의 내용은 100자를 넘을 수 없습니다.")
    private String contents;
}
