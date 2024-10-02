package ru.practicum.shareit.booking.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingDto parseBookingInBookingDto(Booking booking) {
        return new BookingDto(booking.getBooker().getId(), booking.getStart(), booking.getEnd());
    }

    @Override
    public Booking parseBookingDtoInBooking(BookingDto bookingDto) {
        Booking booking = new Booking();

        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());

        return booking;
    }
}
