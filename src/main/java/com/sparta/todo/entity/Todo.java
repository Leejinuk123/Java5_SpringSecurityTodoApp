package com.sparta.todo.entity;

import com.sparta.todo.dto.request.TodoCreateRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
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

    public Todo(TodoCreateRequestDto todoCreateRequestDto){
        this.title = todoCreateRequestDto.getTitle();
        this.contents = todoCreateRequestDto.getContents();
        this.email = todoCreateRequestDto.getEmail();
        this.password = todoCreateRequestDto.getPassword();
    }

    public void update(TodoUpdateRequestDto todoUpdateRequestDto){
        this.setContents(todoUpdateRequestDto.getContents());
        this.setTitle(todoUpdateRequestDto.getTitle());
        this.setEmail(todoUpdateRequestDto.getEmail());
    }
}
