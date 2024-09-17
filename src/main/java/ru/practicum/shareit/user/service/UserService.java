package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

public interface UserService {

    UserDto addNewUser(UserDto user);

    UserDto updateUser(Long userId, UserDto user);

    User getUserById(Long userId);

    void deleteUserById(Long userId);

}
