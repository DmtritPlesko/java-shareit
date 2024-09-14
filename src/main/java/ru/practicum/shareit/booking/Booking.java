package ru.practicum.shareit.booking;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDate;

/**
 * TODO Sprint add-bookings.
 */
@Data
@NonNull
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {

    Long id;

    LocalDate start;

    LocalDate end;

    Item item;

    Long booker;


}
