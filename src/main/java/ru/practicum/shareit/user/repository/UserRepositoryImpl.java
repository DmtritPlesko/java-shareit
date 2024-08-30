package ru.practicum.shareit.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private Map<Long, User> userMap = new HashMap<>();
    private final UserMapper userMapper;

    @Override
    public UserDto addNewUser(User user) {

        user.setId(nextValue());
        userMap.put(user.getId(), user);
        log.info("Добалвение нового пользователя с id = {}", user.getId());
        return userMapper.parseUserInUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto user) {

        User user1 = userMap.get(userId);

        if (Objects.nonNull(user.getName())) {
            user1.setName(user.getName());
        }

        if (Objects.nonNull(user.getEmail())) {
            user1.setEmail(user.getEmail());
        }

        userMap.put(userId, user1);
        log.info("Обновление пользователя с id = {}", user1.getId());
        return userMapper.parseUserInUserDto(user1);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.ofNullable(userMap.get(userId));
    }

    @Override
    public void deleteUserById(Long userId) {
        log.info("удаление пользователя с id = {}", userId);
        userMap.remove(userId);
    }

    @Override
    public List<User> findAll() {
        log.info("Все пользователи");
        return new ArrayList<>(userMap.values());
    }

    private long nextValue() {
        long number = userMap.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);

        return ++number;
    }

}