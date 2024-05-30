package com.sparta.todo.service;

import com.sparta.todo.dto.userRequestDto.LoginRequestDto;
import com.sparta.todo.dto.userRequestDto.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.entity.UserRoleEnum;
import com.sparta.todo.exception.IncorrectPasswordException;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "UserService")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
//        String password = passwordEncoder.encode(requestDto.getPassword());
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
//        String password = requestDto.getPassword();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자명이 존재합니다: " + username);
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, nickname, role);
        userRepository.save(user);
        log.info("회원가입 완료");
    }

//    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
//        String username = requestDto.getUsername();
//        String password = requestDto.getPassword();
//
//        //사용자 확인
//        User user = userRepository.findByUsername(username).orElseThrow(
//                ()-> {
//                    log.error("등록된 사용자가 없습니다. username: {}", username);
//                    return new UsernameNotFoundException("등록된 사용자가 없습니다.");
//                }
//        );
//
//        //비밀번호 확인
//        if (!passwordEncoder.matches(password, user.getPassword())){
//            log.error("비밀번호가 일치하지 않습니다. username: {}", username);
//            throw new IncorrectPasswordException("비밀번호가 일치하지 않습니다.");
//        }
//
//        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
//        jwtUtil.addJwtToHeader(token, res);
//    }
}