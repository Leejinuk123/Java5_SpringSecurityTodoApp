package com.sparta.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;
    @Column(name = "username", nullable = false, unique = true)
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "사용자명은 소문자와 숫자로만 구성된 4자 이상 10자 이하이어야 합니다.")
    private String username;
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 대소문자와 숫자로만 구성된 8자 이상 15자 이하이어야 합니다.")
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Todo> todos = new ArrayList<>();

    public User(String username, String password, String nickname, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
