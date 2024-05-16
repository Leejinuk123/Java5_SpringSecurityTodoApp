package com.sparta.todo.controller;

import com.sparta.todo.dto.request.TodoRequestDto;
import com.sparta.todo.dto.response.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto todoRequestDto){
        return todoService.createTodo(todoRequestDto);
    }
    @GetMapping("/todos")
    public List<TodoResponseDto> getTodos(){
        return todoService.getTodos();
    }
    @GetMapping("/todos/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

}
