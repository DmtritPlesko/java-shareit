package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

public interface BookingMapper {

    BookingDto parseBookingInBookingDto(Booking booking);

    Booking parseBookingDtoInBooking(BookingDto bookingDto);
}
