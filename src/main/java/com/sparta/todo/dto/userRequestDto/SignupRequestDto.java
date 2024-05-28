package com.sparta.todo.dto.userRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
        @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "사용자명은 소문자와 숫자로만 구성된 4자 이상 10자 이하이어야 합니다.")
        private String username;
        @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 대소문자와 숫자로만 구성된 8자 이상 15자 이하이어야 합니다.")
        private String password;
        @NotBlank
        @Size(max = 10)
        private String nickname;
        private boolean admin = false;
        private String adminToken = "";
}
