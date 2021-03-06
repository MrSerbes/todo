package com.todo.todo.service;

import com.todo.todo.dto.ToDoItemDto;
import com.todo.todo.dto.converter.ToDoItemDtoConverter;
import com.todo.todo.dto.request.CreateToDoItemRequest;
import com.todo.todo.dto.request.UpdateToDoItemRequest;
import com.todo.todo.exception.NotFoundException;
import com.todo.todo.model.ItemStatus;
import com.todo.todo.model.ToDoItem;
import com.todo.todo.model.ToDoList;
import com.todo.todo.model.User;
import com.todo.todo.repository.ToDoItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;
    private final ToDoItemDtoConverter toDoItemDtoConverter;

    private final ToDoListService toDoListService;
    private final UserService userService;

    public ToDoItemService(ToDoItemRepository toDoItemRepository, ToDoItemDtoConverter toDoItemDtoConverter, ToDoListService toDoListService, UserService userService) {
        this.toDoItemRepository = toDoItemRepository;
        this.toDoItemDtoConverter = toDoItemDtoConverter;

        this.toDoListService = toDoListService;
        this.userService = userService;
    }

    public ToDoItem findByToDoItemId(String id) {
        return toDoItemRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ToDoItem Not Found"));
    }

    protected List<ToDoItem> findAllToDoItemList() {
        return toDoItemRepository.findAll();
    }

    protected List<ToDoItem> findAllToDoItemListAsc() {
        return toDoItemRepository.findAllByOrderByNameAsc();
    }

    protected List<ToDoItem> findAllByStatusEquals(ItemStatus status) {
        return toDoItemRepository.findAllByStatusEquals(status);
    }

    public ToDoItemDto getToDoItemById(String id) {
        return toDoItemDtoConverter.convert(findByToDoItemId(id));
    }

    public List<ToDoItemDto> getAllToDoListList() {
        return toDoItemDtoConverter.convertToToDoItemDtoList(findAllToDoItemList());
    }

    public List<ToDoItemDto> getAllToDoListListAsc() {
        return toDoItemDtoConverter.convertToToDoItemDtoList(findAllToDoItemListAsc());
    }


    public ToDoItemDto createToDoItem(String userId,String toDoListId, CreateToDoItemRequest request) {

        User user= userService.findByUserId(userId);

        ToDoList toDoList = toDoListService.findByToDoListId(toDoListId);

        if(user==toDoList.getUser()){
            ToDoItem toDoItem = new ToDoItem(
                    request.getName(),
                    request.getDescription(),
                    toDoList
            );
            return toDoItemDtoConverter.convert(toDoItemRepository.save(toDoItem));
        }else {
            throw new NotFoundException("UserId and ToDoListId are invalid");
        }
    }

    public ToDoItemDto updateToDoItem( String userId,
                                       String toDoListId,
                                       String toDoItemId,
                                       UpdateToDoItemRequest request){
        User user= userService.findByUserId(userId);

        ToDoList toDoList = toDoListService.findByToDoListId(toDoListId);

        ToDoItem toDoItem= findByToDoItemId(toDoItemId);

        if(user==toDoList.getUser()&&toDoList==toDoItem.getTodolist()){
            ToDoItem updatedItem = new ToDoItem(
                    toDoItem.getId(),
                    toDoItem.getName(),
                    toDoItem.getDescription(),
                    toDoItem.getCreateDate(),
                    toDoItem.getDeadline(),
                    request.getStatus(),
                    toDoList
            );

            return toDoItemDtoConverter.convert(toDoItemRepository.save(updatedItem));
        }else {
            throw new NotFoundException("UserId and ToDoListId are invalid");
        }

    }

    public String deleteToDoItem(String toDoItemId) {

        getToDoItemById(toDoItemId);
        toDoItemRepository.deleteById(toDoItemId);

        return toDoItemId + " deleted";
    }

}
