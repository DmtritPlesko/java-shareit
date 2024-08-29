package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

public interface UserService {

    UserDto addNewUser(UserDto user);

    UserDto updateUser(Long userId, UserDto user);

    UserDto getUserBuId(Long userId);

    void deleteUserBuId(Long userId);

}
