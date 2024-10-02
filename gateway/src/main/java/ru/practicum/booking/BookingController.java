package ru.practicum.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.booking.dto.BookItemRequestDto;
import ru.practicum.booking.dto.BookingState;


@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BookingController {
    private final BookingClient bookingClient;

    @PostMapping
    public ResponseEntity<Object> createNewBooking(@RequestBody BookItemRequestDto booking,
                                                   @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingClient.createNewBooking(userId, booking);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBookingByID(@PathVariable("bookingId") Long bookingId,
                                                 @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingClient.getBookingById(bookingId, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getUsersBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                  @RequestParam(name = "state",
                                                          required = false,
                                                          defaultValue = "ALL") BookingState state) {
        return bookingClient.getUsersBooking(userId, state);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllUsersItemBookings(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                          @RequestParam(name = "state",
                                                                  required = false,
                                                                  defaultValue = "ALL") BookingState state) {
        return bookingClient.getAllUsersItemBookings(userId, state);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> update(@PathVariable("bookingId") Long bookingId,
                                         @RequestParam(name = "approved") Boolean approved,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingClient.update(bookingId, approved, userId);
    }


}
