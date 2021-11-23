package com.todo.todo.repository;

import com.todo.todo.model.ItemStatus;
import com.todo.todo.model.ToDoItem;
import com.todo.todo.model.ToDoList;
import com.todo.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem,String>, JpaSpecificationExecutor<ToDoItem> {

    //ToDoItem findOneByIdAndTodolist(String id, ToDoList todoList, User owner);

    List<ToDoItem> findAllByOrderByNameAsc();

    List<ToDoItem> findAllByStatusEquals(ItemStatus status);

    //List<Passenger> findByOrderBySeatNumberAsc();

}
