package ru.practicum.shareit.booking;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookingService bookingService;


    User user;
    Item item;
    BookingDto bookingDto;

    @BeforeEach
    void setUp() {
        user = new User();

        user.setId(1L);
        user.setName("Jim");
        user.setEmail("jimJim@icloud.com");

        item = new Item();

        item.setId(1L);
        item.setAvailable(true);
        item.setOwner(1L);
        item.setDescription("qpwoqwkfqwkfpq");
        item.setRequest(new ItemRequest());

        bookingDto = new BookingDto(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(2));

    }

    @Test
    void testAddBooking() {
        when(bookingService.addBooking(bookingDto, 1L)).thenReturn(new Booking());
    }


}