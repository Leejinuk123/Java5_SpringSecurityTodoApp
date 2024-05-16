package com.sparta.todo.entity;

import com.sparta.todo.dto.request.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    public Todo(TodoRequestDto todoRequestDto){
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.email = todoRequestDto.getEmail();
        this.password = todoRequestDto.getPassword();
    }

    public void update(TodoRequestDto todoRequestDto){
        this.setContents(todoRequestDto.getContents());
        this.setTitle(todoRequestDto.getTitle());
        this.setEmail(todoRequestDto.getEmail());
    }
}
