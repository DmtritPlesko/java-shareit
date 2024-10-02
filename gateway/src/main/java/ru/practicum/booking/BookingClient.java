package ru.practicum.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.booking.dto.BookItemRequestDto;
import ru.practicum.booking.dto.BookingState;
import ru.practicum.client.BaseClient;

import java.util.Map;

@Service
public class BookingClient extends BaseClient {
    private static final String API_PREFIX = "/bookings";

    @Autowired
    public BookingClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> createNewBooking(Long userId, BookItemRequestDto booking) {
        return post("", userId, booking);
    }

    public ResponseEntity<Object> getBookingById(Long bookingId, Long userId) {
        return get("/" + bookingId, userId);
    }

    public ResponseEntity<Object> getUsersBooking(Long userId, BookingState state) {
        return get("", userId, Map.of("state", state));
    }

    public ResponseEntity<Object> getAllUsersItemBookings(Long userId, BookingState state) {
        return get("/owner", userId, Map.of("state", state));
    }

    public ResponseEntity<Object> update(Long bookingId, Boolean approved, Long userId) {
        return patch("/" + bookingId + "?approved={approved}",
                userId,
                Map.of("approved", approved),
                null);
    }
}
