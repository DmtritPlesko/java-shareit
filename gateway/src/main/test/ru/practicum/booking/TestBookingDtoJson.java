package ru.practicum.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;
import ru.practicum.booking.dto.BookItemRequestDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
@ContextConfiguration(classes = {BookItemRequestDto.class})
class TestBookingDtoJson {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerialize() throws Exception {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime now = ZonedDateTime.now(zone);

        BookItemRequestDto bookingDto =
                new BookItemRequestDto(
                        1L,
                        now.plusDays(5).toLocalDateTime(),
                        now.plusDays(7).toLocalDateTime()
                );

        String json = objectMapper.writeValueAsString(bookingDto);

        assertThat(json)
                .contains("\"itemId\":1")
                .contains("\"start\":\"")
                .contains("\"end\":\"");
    }


    @Test
    void testDeSerialize() throws JsonProcessingException {
        String json = "{\"itemId\":1,\"start\":\"2024-09-10T11:25:00\",\"end\":\"2024-09-12T00:00:00\"}";
        BookItemRequestDto bookItemRequestDto = objectMapper.readValue(json, BookItemRequestDto.class);

        assertThat(bookItemRequestDto.getItemId()).isEqualTo(1L);

        assertThat(bookItemRequestDto.getStart()).isEqualTo(LocalDateTime.of(2024, 9, 10, 11, 25));

        assertThat(bookItemRequestDto.getEnd()).isEqualTo(LocalDateTime.of(2024, 9, 12, 0, 0));

    }

}