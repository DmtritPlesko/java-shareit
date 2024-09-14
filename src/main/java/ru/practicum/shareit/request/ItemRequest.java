package ru.practicum.shareit.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@NonNull
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {

    @PositiveOrZero
    Long id;

    String description;

    Long requester;

    LocalDate created;
}
