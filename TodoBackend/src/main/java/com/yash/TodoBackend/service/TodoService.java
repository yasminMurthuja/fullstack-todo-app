package com.yash.TodoBackend.service;

import com.yash.TodoBackend.dto.TodoRequest;
import com.yash.TodoBackend.entity.Todo;
import com.yash.TodoBackend.entity.User;
import com.yash.TodoBackend.repositories.TodoRepository;
import com.yash.TodoBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Todo> getTodos(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return todoRepository.findByUser(user);

    }

    public Todo addTodo(String email, TodoRequest req){
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Todo todo = new Todo();
        todo.setTitle(req.getTitle());
        todo.setCompleted((req.isCompleted()));
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id,String email, TodoRequest req){
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
        Todo todo = todoRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Todo not found")
        );

        if(!todo.getUser().getId().equals(user.getId())){
            System.out.println("dont use");
            throw new RuntimeException("Unauthorized to update this todo");
        }
        todo.setTitle(req.getTitle());
        todo.setCompleted(req.isCompleted());
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id, String email){
        Todo todo = todoRepository.findById(id).orElseThrow();
        if(!todo.getUser().getEmail().equals(email)){
            throw new RuntimeException("Unauthorized to delete this todo");
        }
        todoRepository.delete(todo);
    }

}
