package ru.practicum.item.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.practicum.request.model.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"id"})
public class Item {

    Long id;

    String name;

    String description;

    Boolean available;

    Long owner;

    ItemRequest request;
}
