package com.sparta.todo.service;

import com.sparta.todo.dto.commentRequest.CommentCreateRequestDto;
import com.sparta.todo.dto.commentRequest.CommentDeleteRequestDto;
import com.sparta.todo.dto.commentRequest.CommentUpdateRequestDto;
import com.sparta.todo.dto.commentResponse.CommentResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.CommentNotFoundException;
import com.sparta.todo.exception.TodoNotFoundException;
import com.sparta.todo.exception.UnauthorizedUserException;
import com.sparta.todo.exception.UserMismatchException;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(CommentCreateRequestDto createRequestDto, User user) {
        Todo todo = todoRepository.findById(createRequestDto.getTodoId()).orElseThrow(
                () -> new TodoNotFoundException("선택한 id로는 일정은 찾을 수 없습니다: " + createRequestDto.getTodoId())
        );
        Comment comment = new Comment(createRequestDto, todo, user);
        Comment saveComment = commentRepository.save(comment);
        return new CommentResponseDto(saveComment);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentUpdateRequestDto updateRequestDto, User user) {
        Comment comment = findComment(updateRequestDto.getTodoId(), updateRequestDto.getId());
        if (!comment.getUser().isUserMatch(user.getUsername())){
            throw new UserMismatchException("다른 유저의 댓글은 수정할 수 없습니다. "+ user.getUsername());
        }
        comment.update(updateRequestDto);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(CommentDeleteRequestDto deleteRequestDto, User user) {
        Comment comment = findComment(deleteRequestDto.getTodoId(), deleteRequestDto.getId());
        if (!comment.getUser().isUserMatch(user.getUsername())){
            throw new UserMismatchException("다른 유저의 댓글은 삭제할 수 없습니다. "+ user.getUsername());
        }
        commentRepository.delete(comment);
    }

    private Comment findComment(Long todoId, Long commentId){
        return commentRepository.findByTodoIdAndId(todoId,commentId).orElseThrow(
                () -> new CommentNotFoundException("선택한 id로는 댓글을 찾을 수 없습니다: " + commentId)
        );
    }
}
