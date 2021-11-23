package com.todo.todo.repository;

import com.todo.todo.model.ToDoItem;
import com.todo.todo.model.ToDoList;
import com.todo.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem,String> {

    //ToDoItem findOneByIdAndTodolist(String id, ToDoList todoList, User owner);

    List<ToDoItem> findByOrderByNameAsc();
    //List<Passenger> findByOrderBySeatNumberAsc();

}
