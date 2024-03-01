package ar.app.service;

import ar.app.dto.TodoPaginationResponse;
import ar.app.dto.TodoRequest;
import ar.app.dto.TodoResponse;
import ar.app.model.Todo;
import ar.app.repository.TodoRepository;
import ar.app.utils.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository repository;
    private final ObjectMapper mapper;

    public TodoPaginationResponse findAll(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.ASC, "id");

        Page<Todo> todos = repository.findAll(PageRequest.of(page, size, sort));

        List<TodoResponse> responses = todos.stream().map(todo -> mapper.mapData(TodoResponse.class, todo)).toList();

        return TodoPaginationResponse.builder()
                .todos(responses)
                .pageNumber(todos.getPageable().getPageNumber())
                .pageSize(todos.getPageable().getPageSize())
                .totalPages(todos.getTotalPages())
                .totalElements(todos.getTotalElements())
                .build();
    }

    public TodoResponse findById(Long id) {
        return mapper.mapData(TodoResponse.class, repository.findById(id).orElseThrow());
    }

    public TodoResponse create(TodoRequest request) {
        Todo todo = mapper.mapData(Todo.class, request);

        return mapper.mapData(TodoResponse.class, repository.save(todo));
    }

    public TodoResponse update(Long id, TodoRequest request) throws IllegalAccessException {
        Todo todo = repository.findById(id).orElseThrow();

        mapper.mapData(todo, request);

        return mapper.mapData(TodoResponse.class, repository.save(todo));
    }

    public TodoResponse updatePartial(Long id, TodoRequest request) throws IllegalAccessException {
        Todo todo = repository.findById(id).orElseThrow();

        mapper.mapData(todo, request);

        return mapper.mapData(TodoResponse.class, repository.save(todo));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
