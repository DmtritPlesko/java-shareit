package ru.practicum.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemAndCommentDto {

    Long id;

    String name;

    String description;

    Boolean available;

    Long owner;

    Long request;

    LocalDateTime lastBooking;

    LocalDateTime nextBooking;

    List<Comment> comments;

    public ItemAndCommentDto(Long id, String name, String description, Boolean available, Long owner, Long request) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;
        this.request = request;
        comments = new ArrayList<>();
    }
}
