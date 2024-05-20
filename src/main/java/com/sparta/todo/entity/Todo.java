package com.sparta.todo.entity;

import com.sparta.todo.dto.request.TodoCreateRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
import com.sparta.todo.dto.response.TodoResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = true, length = 100)
    private String contents;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    public Todo(TodoCreateRequestDto todoCreateRequestDto){
        this.title = todoCreateRequestDto.getTitle();
        this.contents = todoCreateRequestDto.getContents();
        this.email = todoCreateRequestDto.getEmail();
        this.password = todoCreateRequestDto.getPassword();
    }
    public void update(TodoUpdateRequestDto todoUpdateRequestDto){
        //Dirty Checking
        this.contents = (todoUpdateRequestDto.getContents());
        this.title = (todoUpdateRequestDto.getTitle());
        this.email = (todoUpdateRequestDto.getEmail());
    }
    public boolean checkPassword(String password){
        return Objects.equals(this.getPassword(),password);
    }
}
