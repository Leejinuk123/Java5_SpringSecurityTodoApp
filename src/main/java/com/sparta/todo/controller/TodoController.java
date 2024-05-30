package com.sparta.todo.controller;

import com.sparta.todo.dto.todoRequest.TodoCreateRequestDto;
import com.sparta.todo.dto.todoRequest.TodoUpdateRequestDto;
import com.sparta.todo.dto.todoResponse.TodoResponseDto;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j(topic = "TodoController")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public TodoResponseDto createTodo(@Valid @RequestBody TodoCreateRequestDto todoCreateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.createTodo(todoCreateRequestDto, userDetails.getUser());
    }
    @GetMapping("/todos")
    public List<TodoResponseDto> getTodos(){
        return todoService.getTodos();
    }
    @GetMapping("/todos/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }
    @PutMapping("/todos/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id,@Valid @RequestBody TodoUpdateRequestDto todoUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.updateTodo(id, todoUpdateRequestDto, userDetails.getUser());
    }
    @DeleteMapping("/todos/{id}")
    public Long getTodo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.deleteTodo(id, userDetails.getUser());
    }
}
