package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

public interface UserService {

    UserDto addNewUser(UserDto user);

    UserDto updateUser(Long userId, UserDto user);

    UserDto getUserById(Long userId);

    void deleteUserById(Long userId);

}
