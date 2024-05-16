package com.sparta.todo.service;

import com.sparta.todo.dto.request.TodoDeleteRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
import com.sparta.todo.dto.response.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = new Todo(todoUpdateRequestDto);
        Todo saveTodo = todoRepository.save(todo);
        TodoResponseDto todoResponseDto = new TodoResponseDto(saveTodo);
        return todoResponseDto;
    }
    public TodoResponseDto getTodo(Long id) {
        Todo todo = findTodo(id);
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
        return todoResponseDto;
    }
    public List<TodoResponseDto> getTodos() {
        return todoRepository.findAllByOrderByModifiedAtDesc().stream().map(TodoResponseDto::new).toList();
    }

    private Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }
    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = findTodo(id);
        if (todo.getPassword().equals(todoUpdateRequestDto.getPassword())){
            todo.update(todoUpdateRequestDto);
            TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
            return todoResponseDto;
        } else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
    public Long deleteTodo(Long id, TodoDeleteRequestDto todoDeleteRequestDto) {
        Todo todo = findTodo(id);
        if (todo.getPassword().equals(todoDeleteRequestDto.getPassword())){
            todoRepository.delete(todo);
            return id;
        } else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
