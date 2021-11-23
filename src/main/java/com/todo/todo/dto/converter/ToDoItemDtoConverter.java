package com.todo.todo.dto.converter;

import com.todo.todo.dto.ToDoItemDto;
import com.todo.todo.model.ToDoItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToDoItemDtoConverter {

    public ToDoItemDto convert(ToDoItem from) {
        return new ToDoItemDto(
                from.getId(),
                from.getName(),
                from.getDescription(),
                from.getCreateDate(),
                from.getDeadline(),
                from.getStatus()
                );
    }

    public List<ToDoItemDto> convertToToDoItemDtoList(List<ToDoItem> toDoItemList) {
        return toDoItemList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
