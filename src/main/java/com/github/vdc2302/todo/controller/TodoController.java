package com.github.vdc2302.todo.controller;

import com.github.vdc2302.todo.model.Todo;
import com.github.vdc2302.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
  private final TodoService service;

//  get all
  @GetMapping
  public List<Todo> getAll(){
    return service.getAllTodos();
  }

// get by id
  @GetMapping("{id}")
  public ResponseEntity<Todo> getById(@PathVariable Long id){
    return ResponseEntity.ok(service.getTodoById(id));
  }

//  create new
  @PostMapping
  public ResponseEntity<Todo> create(@Valid @RequestBody Todo todo){
    return new ResponseEntity<>(service.createTodo(todo), HttpStatus.CREATED);
  }

//  update existing
  @PutMapping("{id}")
  public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo){
    return ResponseEntity.ok(service.updateTodo(id, todo));
  }

//  delete
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.deleteTodo(id);
    return ResponseEntity.noContent().build();
  }

}
