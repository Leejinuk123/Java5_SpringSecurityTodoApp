package com.sparta.todo.controller;

import com.sparta.todo.dto.userRequestDto.LoginRequestDto;
import com.sparta.todo.dto.userRequestDto.SignupRequestDto;
import com.sparta.todo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("회원가입이 되었습니다.");
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto, res);
            return ResponseEntity.status(HttpStatus.OK).body("로그인이 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 실패했습니다.");
        }
    }
}
