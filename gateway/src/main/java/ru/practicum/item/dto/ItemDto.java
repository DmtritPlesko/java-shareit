package ru.practicum.item.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

/**
 * TODO Sprint add-controllers.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"id"})
public class ItemDto {

    Long id;

    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotBlank
    Boolean available;

    Long requestId;

}
