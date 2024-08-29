package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto addNewUser(UserDto user) {

        validation(user);
        final User user1 = userRepository.addNewUser(userMapper.parseUserDtoInUser(user));

        return userMapper.parseUserInUserDto(user1);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto user) {
        searchDuplicateEmailBuUser(user);
        final User user1 = userRepository.updateUser(userId, user);

        return userMapper.parseUserInUserDto(user1);
    }

    @Override
    public UserDto getUserBuId(Long userId) {
        checkId(userId);

        final Optional<User> user = userRepository.getUserBuId(userId);

        return userMapper.parseUserInUserDto(user.get());
    }

    @Override
    public void deleteUserBuId(Long userId) {
        userRepository.deleteUserBuId(userId);
    }

    private void validation(UserDto userDto) {
        String startMessage = "Ошибка валидации: ";
        if (userDto.getName() == null) {
            log.error("Имя пользователя не должно быть пустым");
            throw new ValidationException(startMessage + "Имя пользователя не должно быть пустым");
        } else if (userDto.getName().isBlank()) {
            log.error("Имя пользователя не должно быть из пробелов");
            throw new ValidationException(startMessage + "Имя пользователя не должно быть из пробелов");
        } else if (userDto.getEmail().isBlank()) {
            log.error("Почта не може быть пустой");
            throw new ValidationException(startMessage + "Почта не може быть пустой");
        }
        searchDuplicateEmailBuUser(userDto);
    }

    private void searchDuplicateEmailBuUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(elem -> Objects.equals(elem.getEmail(), userDto.getEmail()))
                .findFirst();

        if (userOptional.isPresent()) {
            log.error("Невозможно добавить пользователя с одинаковым email");
            throw new IllegalArgumentException("Невозможно добавить пользователя с одинаковым email");
        }

    }

    private void checkId(Long userId) {

        if (userRepository.getUserBuId(userId).isEmpty()) {
            log.error("Пользователь с id = " + userId + " не найден");
            throw new NotFoundException("Пользователь с id = " + userId + " не найден");
        }
    }
}