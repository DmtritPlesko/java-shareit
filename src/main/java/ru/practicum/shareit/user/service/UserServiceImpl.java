package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addNewUser(User user) {
        validation(user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        User user1 = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Нет такого пользователя"));
        if (user.getName() != null) {
            user1.setName(user.getName());
        }

        if (user.getEmail() != null) {
            user1.setEmail(user.getEmail());
        }

        return userRepository.save(user1);
    }

    @Override
    public User getUserById(Long userId) {
        checkId(userId);

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Пользователь не найден");
        }
        return userOptional.get();
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    private void validation(User userDto) {
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
    }

    private void checkId(Long userId) {

        if (userRepository.findById(userId).isEmpty()) {
            log.error("Пользователь с id = " + userId + " не найден");
            throw new NotFoundException("Пользователь с id = " + userId + " не найден");
        }
    }
}