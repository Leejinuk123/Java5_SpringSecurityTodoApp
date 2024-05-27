package com.sparta.todo.service;

import com.sparta.todo.dto.commentRequest.CommentCreateRequestDto;
import com.sparta.todo.dto.commentRequest.CommentUpdateRequestDto;
import com.sparta.todo.dto.commentResponse.CommentResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.exception.TodoNotFoundException;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(CommentCreateRequestDto createRequestDto) {
        Todo todo = todoRepository.findById(createRequestDto.getTodoId()).orElseThrow(
                () -> new TodoNotFoundException("선택한 id로는 일정은 찾을 수 없습니다: " + createRequestDto.getTodoId())
        );
        Comment comment = new Comment(createRequestDto, todo);
        Comment saveComment = commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }
}
