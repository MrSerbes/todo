package com.todo.todo.service;

import com.todo.todo.dto.UserDto;
import com.todo.todo.dto.converter.UserDtoConverter;
import com.todo.todo.dto.request.CreateUserRequest;
import com.todo.todo.exception.NotFoundException;
import com.todo.todo.model.User;
import com.todo.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository,
                       UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    protected User findByUserId(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User not Found"));
    }

    protected List<User> getAllUserList() {
        return userRepository.findAll();
    }

    public UserDto getUserById(String id) {
        return userDtoConverter.convert(findByUserId(id));
    }

    public List<UserDto> getUserList() {
        return userDtoConverter.convertToUserDtoList(getAllUserList());
    }

    public UserDto createUser(CreateUserRequest request) {

        User user = new User(
                request.getName(),
                request.getEmail(),
                Collections.emptyList()
        );

        return userDtoConverter.convert(userRepository.save(user));
    }

    public String deleteUser(String userId) {

        getUserById(userId);
        userRepository.deleteById(userId);
        return userId + " deleted";
    }

   /* public UserDto updateUser(String userId, UpdateUserRequest request) {

        User user = findByUserId(userId);

        User updatedUser = new User(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                request.getDisplayName(),
                user.isActive(),
                user.getToDoLists(),
                user.getComments(),
                user.getCreatedAt(),
                LocalDateTime.now()
        );

        return userDtoConverter.convert(userRepository.save(updatedUser));
    }*/
}
