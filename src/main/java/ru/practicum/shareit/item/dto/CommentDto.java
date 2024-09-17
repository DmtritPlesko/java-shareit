package ru.practicum.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {
    Long id;

    String text;

    String authorName;

    Item item;

    LocalDateTime created;

    public CommentDto(Long id, String text, Item item, String author, LocalDateTime created) {
        this.id = id;
        this.text = text;
        this.item = item;
        this.authorName = author;
        this.created = created;
    }
}
