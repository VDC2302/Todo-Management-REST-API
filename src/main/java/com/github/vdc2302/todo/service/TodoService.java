package com.github.vdc2302.todo.service;

import com.github.vdc2302.todo.model.Todo;
import com.github.vdc2302.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository repository;

  public List<Todo> getAllTodos(){
    return repository.findAll();
  }

  public Todo getTodoById(Long id){
    return repository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Todo not found with ID: " + id));
  }

  public Todo createTodo(Todo todo){
    todo.setTitle(todo.getTitle().trim());
    if(todo.getCompleted() == null){
      todo.setCompleted(false);
    }
    return repository.save(todo);
  }

  public Todo updateTodo(Long id, Todo details){
    Todo existingTodo = getTodoById(id);

    existingTodo.setTitle(details.getTitle());
    existingTodo.setDescription(details.getDescription());
    existingTodo.setCompleted(details.getCompleted());

    return repository.save(existingTodo);
  }

  public void deleteTodo(Long id){
    if(!repository.existsById(id)){
      throw new NoSuchElementException("Cannot delete because todo not found with ID: " + id);
    }
    repository.deleteById(id);
  }
}
