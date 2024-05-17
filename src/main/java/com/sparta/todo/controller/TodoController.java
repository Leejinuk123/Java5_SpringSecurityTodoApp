package com.sparta.todo.controller;

import com.sparta.todo.dto.request.TodoCreateRequestDto;
import com.sparta.todo.dto.request.TodoDeleteRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
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
    public TodoResponseDto createTodo(@RequestBody TodoCreateRequestDto todoCreateRequestDto){
        return todoService.createTodo(todoCreateRequestDto);
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
    public TodoResponseDto getTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto){
        return todoService.updateTodo(id, todoUpdateRequestDto);
    }
    @DeleteMapping("/todos/{id}")
    public Long getTodo(@PathVariable Long id, @RequestBody TodoDeleteRequestDto todoDeleteRequestDto){
        return todoService.deleteTodo(id, todoDeleteRequestDto);
    }
}
