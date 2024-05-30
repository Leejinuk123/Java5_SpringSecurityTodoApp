package com.sparta.todo.dto.todoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class TodoCreateRequestDto {
    @Size(max = 50, message = "title 은 50자를 넘을 수 없습니다.")
    @NotBlank(message = "title 은 필수입니다.")
    private String title;
    @Size(max = 100, message = "contents 는 100자를 넘을 수 없습니다.")
    private String contents;
}
