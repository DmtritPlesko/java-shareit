package ru.practicum.shareit.user.mapper;

import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.dto.UserDto;

public interface UserMapper {

    UserDto parseUserInUserDto(User user);

    User parseUserDtoInUser(UserDto userDto);
}
