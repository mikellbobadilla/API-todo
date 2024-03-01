package ar.app.controller;

import ar.app.dto.TodoPaginationResponse;
import ar.app.dto.TodoRequest;
import ar.app.dto.TodoResponse;
import ar.app.model.Todo;
import ar.app.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/todos", headers = "Accept=application/json")
@AllArgsConstructor
public class TodoController {

    private final TodoService service;

    @GetMapping
    public ResponseEntity<TodoPaginationResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) throws IllegalAccessException {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponse> updatePartial(@PathVariable Long id, @RequestBody TodoRequest request) throws IllegalAccessException {
        return ResponseEntity.ok(service.updatePartial(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
