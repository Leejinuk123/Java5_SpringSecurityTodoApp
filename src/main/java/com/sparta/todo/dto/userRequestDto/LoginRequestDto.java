package com.sparta.todo.dto.userRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank(message = "username 은 필수입니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "사용자명은 소문자와 숫자로만 구성된 4자 이상 10자 이하이어야 합니다.")
    private String username;
    @NotBlank(message = "password 는 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 대소문자와 숫자로만 구성된 8자 이상 15자 이하이어야 합니다.")
    private String password;
}
