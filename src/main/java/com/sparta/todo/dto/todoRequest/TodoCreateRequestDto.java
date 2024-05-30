package com.sparta.todo.dto.todoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class TodoCreateRequestDto {
    @Size(max = 50)
    @NotBlank
    private String title;
    @Size(max = 100)
    private String contents;
//    @NotBlank
//    private String username;
//    @NotBlank
//    private String password;
}
