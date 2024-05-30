package com.sparta.todo.service;

import com.sparta.todo.dto.todoRequest.TodoCreateRequestDto;
import com.sparta.todo.dto.todoRequest.TodoUpdateRequestDto;
import com.sparta.todo.dto.todoResponse.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.TodoStatusEnum;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.TodoAlreadyDeletedException;
import com.sparta.todo.exception.TodoNotFoundException;
import com.sparta.todo.exception.UserMismatchException;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j(topic = "TodoService")
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoCreateRequestDto todoCreateRequestDto, User user) {
        log.info(user.getUsername());
        Todo todo = new Todo(todoCreateRequestDto, user);
        Todo saveTodo = todoRepository.save(todo);
        TodoResponseDto todoResponseDto = new TodoResponseDto(saveTodo);
        return todoResponseDto;
    }
    public TodoResponseDto getTodo(Long id) {
        Todo todo = findTodo(id);
        if (todo.isDeleted()) throw new TodoAlreadyDeletedException("선택한 id의 일정 정보가 삭제되어 조회할 수 없습니다: " + id);
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
        return todoResponseDto;
    }
    public List<TodoResponseDto> getTodos() {
        return todoRepository.findAllByStatusOrderByModifiedAtDesc(TodoStatusEnum.ACTIVE)
                                .stream()
                                .map(TodoResponseDto::new)
                                .toList();
    }
    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoUpdateRequestDto todoUpdateRequestDto, User user) {
        Todo todo = findTodo(id);

        if (todo.isDeleted()) throw new TodoAlreadyDeletedException("선택한 id의 일정 정보가 삭제되어 수정할 수 없습니다: " + id);

        if (todo.getUser().isUserMatch(user.getUsername())){
            todo.update(todoUpdateRequestDto);
            TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
            return todoResponseDto;
        }
        throw new UserMismatchException("다른 유저의 일정은 수정할 수 없습니다. " + id);
    }
    @Transactional
    public Long deleteTodo(Long id, User user) {
        Todo todo = findTodo(id);

        if (todo.isDeleted()) throw new TodoAlreadyDeletedException("선택한 id의 일정 정보가 이미 삭제되어 삭제할 수 없습니다: " + id);

        if (todo.getUser().isUserMatch(user.getUsername())){
            todo.delete();
            return id;
        }
        throw new UserMismatchException("다른 유저의 일정은 삭제할 수 없습니다. " + id);
    }
    private Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new TodoNotFoundException("선택한 id로는 일정은 찾을 수 없습니다: " + id)
        );
    }
}
