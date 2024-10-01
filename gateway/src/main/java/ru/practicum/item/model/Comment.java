package ru.practicum.item.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    Long id;

    String text;

    Item item;

    User authorId;

    LocalDateTime created;
}
