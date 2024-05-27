package com.sparta.todo.entity;

import com.sparta.todo.dto.commentRequest.CommentCreateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content", nullable = false, length = 30)
    private String content;
    @Column(name = "userName", nullable = false)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(CommentCreateRequestDto createRequestDto, Todo todo){
        this.userName =  createRequestDto.getUserName();
        this.content = createRequestDto.getContent();
        this.todo = todo;
    }

}
