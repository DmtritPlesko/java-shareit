package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.model.User;

public interface UserService {

    User addNewUser(User user);

    User updateUser(Long userId, User user);

    User getUserById(Long userId);

    void deleteUserById(Long userId);

}
