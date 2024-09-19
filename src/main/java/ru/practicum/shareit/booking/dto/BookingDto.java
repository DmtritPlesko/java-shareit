package ru.practicum.shareit.booking.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto {

    Long itemId;
    LocalDateTime start;
    LocalDateTime end;

    public BookingDto(Long itemId, LocalDateTime start, LocalDateTime end) {
        this.itemId = itemId;
        this.start = start;
        this.end = end;
    }
}
