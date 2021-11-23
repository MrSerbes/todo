package com.todo.todo.repository;

import com.todo.todo.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList,String> {
    List<ToDoList> findAllByUserId(String userId);
}
