package com.todo.todo.service;

import com.todo.todo.dto.ToDoListDto;
import com.todo.todo.dto.converter.ToDoListDtoConverter;
import com.todo.todo.dto.request.CreateToDoListRequest;
import com.todo.todo.exception.NotFoundException;
import com.todo.todo.model.ToDoList;
import com.todo.todo.model.User;
import com.todo.todo.repository.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final ToDoListDtoConverter toDoListDtoConverter;

    private final UserService userService;

    public ToDoListService(ToDoListRepository toDoListRepository,
                       ToDoListDtoConverter toDoListDtoConverter, UserService userService) {
        this.toDoListRepository = toDoListRepository;
        this.toDoListDtoConverter = toDoListDtoConverter;
        this.userService = userService;
    }

    public ToDoList findByToDoListId(String id) {
        return toDoListRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ToDoList not Found"));
    }

    protected List<ToDoList> findAllToDoListList() {
        return toDoListRepository.findAll();
    }
//            return toDoListDtoConverter.convertToToDoListDtoList(getAllToDoListList());

    protected List<ToDoList> getAllToDoListByUserId(String userId) {
        return toDoListRepository.findAllByUserId(userId);
    }

    public ToDoListDto getToDoListById(String id) {
        return toDoListDtoConverter.convert(findByToDoListId(id));
    }

    public List<ToDoListDto> getAllToDoListDtoList() {
        return toDoListDtoConverter.convertToToDoListDtoList(findAllToDoListList());
    }

    public List<ToDoListDto> getAllToDoListDtoByUserId(String userId) {
        return toDoListDtoConverter.convertToToDoListDtoList(getAllToDoListByUserId(userId));
    }

    public ToDoListDto createToDoList(String userId, CreateToDoListRequest request) {

        User user = userService.findByUserId(userId);

        ToDoList toDoList = new ToDoList(
                request.getName(),
                user
        );

        return toDoListDtoConverter.convert(toDoListRepository.save(toDoList));
    }

    public String deleteToDoList(String toDoListId) {

        getToDoListById(toDoListId);
        toDoListRepository.deleteById(toDoListId);

        return toDoListId + " deleted";
    }



   /* public ToDoListDto updateToDoList(String toDoListId, UpdateToDoListRequest request) {

        ToDoList toDoList = findByToDoListId(toDoListId);

        ToDoList updatedToDoList = new ToDoList(
                toDoList.getId(),
                request.getTitle(),
                request.getBody(),
                toDoList.getCreatedAt(),
                LocalDateTime.now(),
                request.getStatus(),
                toDoList.getUser(),
                toDoList.getComments()
        );

        return toDoListDtoConverter.convert(toDoListRepository.save(updatedToDoList));
    }*/
}
