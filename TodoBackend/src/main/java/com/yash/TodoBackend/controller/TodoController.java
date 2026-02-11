package com.yash.TodoBackend.controller;


import com.yash.TodoBackend.dto.TodoRequest;
import com.yash.TodoBackend.entity.Todo;
import com.yash.TodoBackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService service;

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(Authentication auth){

        List<Todo> todos = service.getTodos(auth.getName());
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody TodoRequest req, Authentication auth){
        if (auth == null) {
            System.out.println("You auth is null");
            throw new RuntimeException("AUTH IS NULL – JWT FILTER FAILED");
        }
        Todo createTodo = service.addTodo(auth.getName(), req);
        return new ResponseEntity<>(createTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoRequest req, Authentication authentication){
        if(authentication == null){
            throw new RuntimeException("Authentication is null");
        }
        Todo updateTodo = service.updateTodo(id, authentication.getName(),req);
        return new ResponseEntity<>(updateTodo,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id, Authentication auth){
        service.deleteTodo(id, auth.getName());
        return ResponseEntity.ok().build();
    }
}
