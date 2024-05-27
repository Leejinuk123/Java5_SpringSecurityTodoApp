package com.sparta.todo.dto.todoRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
@Getter
public class TodoDeleteRequestDto {
    @NotNull
    private String password;
}
