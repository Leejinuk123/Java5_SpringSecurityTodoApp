package com.sparta.todo.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class TodoCreateRequestDto {
    @Size(max = 50)
    @NotNull
    private String title;
    @Size(max = 100)
    private String contents;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
