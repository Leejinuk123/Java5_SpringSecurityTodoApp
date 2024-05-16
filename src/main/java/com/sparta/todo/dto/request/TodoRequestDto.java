package com.sparta.todo.dto.request;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String title;
    private String contents;
    private String email;
    private String password;
}
