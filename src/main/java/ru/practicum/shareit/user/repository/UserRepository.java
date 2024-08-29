package ru.practicum.shareit.user.repository;

import java.util.List;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.Optional;


public interface UserRepository {

    User addNewUser(User user);

    User updateUser(Long userId, UserDto user);

    Optional<User> getUserBuId(Long userId);

    void deleteUserBuId(Long userId);

    List<User> findAll();
}
