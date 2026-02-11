package com.yash.TodoBackend.repositories;

import com.yash.TodoBackend.entity.Todo;
import com.yash.TodoBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByUser(User user);
    Optional<Todo> findById(Todo todo);
}
