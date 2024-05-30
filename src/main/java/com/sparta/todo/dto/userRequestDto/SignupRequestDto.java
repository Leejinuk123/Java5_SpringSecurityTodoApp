package com.sparta.todo.dto.userRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
        @NotBlank(message = "username 은 필수입니다.")
        @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "사용자명은 소문자와 숫자로만 구성된 4자 이상 10자 이하이어야 합니다.")
        private String username;
        @NotBlank(message = "password 은 필수입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 대소문자와 숫자로만 구성된 8자 이상 15자 이하이어야 합니다.")
        private String password;
        @NotBlank(message = "nickname 은 필수입니다.")
        @Size(max = 10, message = "nickname 은 10자를 넘을 수 없습니다.")
        private String nickname;
        private boolean admin = false;
        private String adminToken = "";
}
