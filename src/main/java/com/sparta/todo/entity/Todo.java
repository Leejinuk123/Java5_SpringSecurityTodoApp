package com.sparta.todo.entity;

import com.sparta.todo.dto.request.TodoCreateRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TodoStatus status;

    public Todo(TodoCreateRequestDto todoCreateRequestDto){
        this.title = todoCreateRequestDto.getTitle();
        this.contents = todoCreateRequestDto.getContents();
        this.email = todoCreateRequestDto.getEmail();
        this.password = todoCreateRequestDto.getPassword();
        this.status = TodoStatus.ACTIVE;
    }
    public void update(TodoUpdateRequestDto todoUpdateRequestDto){
        //Dirty Checking
        this.contents = (todoUpdateRequestDto.getContents());
        this.title = (todoUpdateRequestDto.getTitle());
        this.email = (todoUpdateRequestDto.getEmail());
    }
    public void delete(){
        this.status = TodoStatus.DELETED;
    }
    public boolean isPasswordValid(String password){
        return Objects.equals(this.getPassword(),password);
    }
    public boolean isDeleted() {
        return this.status == TodoStatus.DELETED;
    }
}
