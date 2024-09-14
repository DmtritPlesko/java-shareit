package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.status.Status;

import java.util.List;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking createNewBooking(@RequestBody BookingDto booking,
                                    @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.addBooking(booking, userId);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingByID(@PathVariable("bookingId") Long bookingId,
                                  @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.getBookingById(bookingId, userId);
    }

    @GetMapping
    public List<Booking> getUsersBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                         @RequestParam(name = "state",
                                                 required = false,
                                                 defaultValue = "ALL") String state) {
        return bookingService.getAllBookingByUserId(userId, state);
    }

    @GetMapping("/owner")
    public List<Booking> getAllUsersItemBookings(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                 @RequestParam(name = "state",
                                                         required = false,
                                                         defaultValue = "ALL") Status state) {

        return bookingService.getAllUsersItemBookings(userId, state);
    }

    @PatchMapping("/{bookingId}")
    public Booking update(@PathVariable("bookingId") Long bookingId,
                          @RequestParam(name = "approved") Boolean approved,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.updateBooking(bookingId, approved, userId);
    }

}
