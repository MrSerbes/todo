package com.todo.todo.dto.converter;

import com.todo.todo.dto.ToDoItemDto;
import com.todo.todo.dto.ToDoListDto;
import com.todo.todo.dto.UserDto;
import com.todo.todo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return new UserDto(
                from.getId(),
                from.getName(),
                from.getEmail(),
                from.getTodos()
                        .stream()
                        .map(p -> new ToDoListDto(
                                p.getId(),
                                p.getTitle(),
                                p.getToDoItems()
                                        .stream()
                                        .map(s -> new ToDoItemDto(
                                                s.getId(),
                                                s.getName(),
                                                s.getDescription(),
                                                s.getCreateDate(),
                                                s.getCreateDate(),
                                                s.getStatus()
                                        )).collect(Collectors.toList())
                                )
                        )
                        .collect(Collectors.toList()));

    }

    public List<UserDto> convertToUserDtoList(List<User> users) {
        return users
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
