package ru.practicum.item.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;
import ru.practicum.item.model.Item;
import ru.practicum.request.model.ItemRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
@ContextConfiguration(classes = {CommentDto.class})
class CommentDtoTest {

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void testSerializable() throws JsonProcessingException {
        Item item = new Item();

        item.setId(1L);
        item.setRequest(new ItemRequest());
        item.setAvailable(true);
        item.setName("KNIFE");
        item.setOwner(1L);
        item.setDescription("MFIOWFMIOW");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 9, 30, 12, 24);

        CommentDto commentDto = new CommentDto(
                1L,
                "HALLOW",
                item,
                "AUTHOR",
                localDateTime
        );

        String json = objectMapper.writeValueAsString(commentDto);

        assertThat(json)
                .contains("\"id\":1")
                .contains("\"text\":\"HALLOW\"")
                .contains("\"authorName\":\"AUTHOR\"")
                .contains("\"created\":\"2024-09-30T12:24:00");
    }

    @Test
    void testDeSerializable() throws JsonProcessingException {
        String json = "{\"id\":1,\"text\":\"HALLOW\",\"authorName\":\"AUTHOR\",\"created\":\"2024-09-30T12:24:00\"}";

        CommentDto commentDto = objectMapper.readValue(json, CommentDto.class);

        assertThat(commentDto.getId()).isEqualTo(1L);
        assertThat(commentDto.getText()).isEqualTo("HALLOW");
        assertThat(commentDto.getAuthorName()).isEqualTo("AUTHOR");
        assertThat(commentDto.getCreated()).isEqualTo(LocalDateTime.of(2024, 9, 30, 12, 24));
    }

}