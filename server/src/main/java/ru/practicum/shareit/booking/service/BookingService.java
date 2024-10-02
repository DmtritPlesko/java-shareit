package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.status.Status;

import java.util.List;

public interface BookingService {

    Booking addBooking(BookingDto booking, Long ownerId);

    Booking updateBooking(Long bookingId, Boolean approved, Long ownerId);

    void deleteBookingById(Long bookingId);

    Booking getBookingById(Long bookingId, Long ownerId);

    List<Booking> getAllBooking();

    List<Booking> getAllBookingByUserId(Long userId, String state);

    List<Booking> getAllUsersItemBookings(Long userId, Status state);
}
