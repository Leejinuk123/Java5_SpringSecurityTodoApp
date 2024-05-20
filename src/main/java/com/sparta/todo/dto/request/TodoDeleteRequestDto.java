package com.sparta.todo.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
@Getter
public class TodoDeleteRequestDto {
    @NotNull
    private String password;
}
