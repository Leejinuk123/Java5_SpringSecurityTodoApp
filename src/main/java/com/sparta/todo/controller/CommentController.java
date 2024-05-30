package com.sparta.todo.controller;

import com.sparta.todo.dto.commentRequest.CommentCreateRequestDto;
import com.sparta.todo.dto.commentRequest.CommentDeleteRequestDto;
import com.sparta.todo.dto.commentRequest.CommentUpdateRequestDto;
import com.sparta.todo.dto.commentResponse.CommentResponseDto;
import com.sparta.todo.exception.ErrorResponse;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody @Valid CommentCreateRequestDto createRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(createRequestDto, userDetails.getUser());
    }
    @PutMapping("/comment")
    public CommentResponseDto updateComment(@RequestBody @Valid CommentUpdateRequestDto updateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(updateRequestDto, userDetails.getUser());
    }
    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteComment(@RequestBody @Valid CommentDeleteRequestDto deleteRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(deleteRequestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK)
                .body("댓글이 삭제되었습니다.");
    }
}
