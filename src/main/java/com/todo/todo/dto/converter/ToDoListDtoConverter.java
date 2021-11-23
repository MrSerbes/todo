package com.todo.todo.dto.converter;

import com.todo.todo.dto.ToDoItemDto;
import com.todo.todo.dto.ToDoListDto;
import com.todo.todo.dto.UserDto;
import com.todo.todo.model.ToDoList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToDoListDtoConverter {

    public ToDoListDto convert(ToDoList from) {

        return new ToDoListDto(
                from.getId(),
                from.getTitle(),
                from.getToDoItems()
                        .stream()
                        .map(p -> new ToDoItemDto(
                                p.getId(),
                                p.getName(),
                                p.getDescription(),
                                p.getCreateDate(),
                                p.getDeadline(),
                                p.getStatus()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public List<ToDoListDto> convertToToDoListDtoList(List<ToDoList> allToDoListList) {
        return allToDoListList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
