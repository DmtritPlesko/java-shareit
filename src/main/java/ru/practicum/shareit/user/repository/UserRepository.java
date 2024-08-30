package ru.practicum.shareit.user.repository;

import java.util.List;

import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.Optional;


public interface UserRepository {

    UserDto addNewUser(User user);

    UserDto updateUser(Long userId, UserDto user);

    Optional<User> getUserById(Long userId);

    void deleteUserById(Long userId);

    List<User> findAll();
}
