package com.todo.todo.controller;

import com.todo.todo.dto.ToDoItemDto;
import com.todo.todo.dto.ToDoListDto;
import com.todo.todo.dto.UserDto;
import com.todo.todo.dto.request.CreateToDoItemRequest;
import com.todo.todo.dto.request.CreateToDoListRequest;
import com.todo.todo.dto.request.CreateUserRequest;
import com.todo.todo.dto.request.UpdateToDoItemRequest;
import com.todo.todo.service.ToDoItemService;
import com.todo.todo.service.ToDoListService;
import com.todo.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/v1/user")
public class UserController {

    /*

     /todo/{listId}/list_item/{listItem}

     */
    private final UserService userService;
    private final ToDoListService toDoListService;
    private final ToDoItemService toDoItemService;

    public UserController(UserService userService,
                          ToDoListService toDoListService,
                          ToDoItemService toDoItemService) {

        this.userService = userService;
        this.toDoListService = toDoListService;
        this.toDoItemService = toDoItemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/{userId}/todos")
    public ResponseEntity<List<ToDoListDto>> getAllToDoListById(@PathVariable String userId) {
        return ResponseEntity.ok(toDoListService.getAllToDoListDtoByUserId(userId));
    }

    @GetMapping("/{userId}/todo/{toDoListId}")
    public ResponseEntity<ToDoListDto> getToDoListById(@PathVariable String toDoListId) {
        return ResponseEntity.ok(toDoListService.getToDoListById(toDoListId));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ToDoListDto> createToDoList(@PathVariable String userId,
                                                      @Valid @RequestBody CreateToDoListRequest request) {

        return ResponseEntity.ok(toDoListService.createToDoList(userId, request));
    }

    @PostMapping("/{userId}/todo/{toDoListId}")
    public ResponseEntity<ToDoItemDto> createToDoItem(@PathVariable String userId ,
                                                      @PathVariable String toDoListId,
                                                      @Valid @RequestBody CreateToDoItemRequest request){

        return ResponseEntity.ok(toDoItemService.createToDoItem(userId,toDoListId,request));
    }

    @PutMapping("/{userId}/todo/{toDoListId}/item/{toDoItemId}")
    public ResponseEntity<ToDoItemDto> updateToDoItem(@PathVariable String userId,
                                                      @PathVariable String toDoListId,
                                                      @PathVariable String toDoItemId,
                                                      @Valid @RequestBody UpdateToDoItemRequest request) {

        return ResponseEntity.ok(toDoItemService.updateToDoItem(userId, toDoListId, toDoItemId,request));
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @DeleteMapping("/{userId}/todo/{toDoListId}")
    public ResponseEntity<String> deleteToDoList(@PathVariable String toDoListId) {
        return ResponseEntity.ok(toDoListService.deleteToDoList(toDoListId));
    }

    @DeleteMapping("/{userId}/todo/{toDoListId}/item/{toDoItemId}")
    public ResponseEntity<String> deleteToDoItem(@PathVariable String toDoItemId) {
        return ResponseEntity.ok(toDoItemService.deleteToDoItem(toDoItemId));
    }


   /* @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId,
                                              @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }*/
}
