package ru.practicum.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.request.model.ItemRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ItemAndCommentDto {

    Long id;

    String name;

    String description;

    Boolean available;

    Long owner;

    ItemRequest request;

    LocalDateTime lastBooking;

    LocalDateTime nextBooking;

    List<Comment> comments;

    public ItemAndCommentDto(Long id, String name, String description, Boolean available, Long owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;
        comments = new ArrayList<>();
    }
}
