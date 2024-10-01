package ru.practicum.shareit.user.mapper;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

public interface UserMapper {

    UserDto parseUserInUserDto(User user);

    User parseUserDtoInUser(UserDto userDto);
}
