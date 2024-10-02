package ru.practicum.item.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ContextConfiguration(classes = {ItemDto.class})
class ItemDtoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializableEqualsRequestId() throws Exception {
        ItemDto itemDto = new ItemDto();

        itemDto.setId(1L);
        itemDto.setName("KNIFE");
        itemDto.setDescription("Hallow WORLD");
        itemDto.setAvailable(true);
        itemDto.setRequestId(1L);

        String json = objectMapper.writeValueAsString(itemDto);

        assertThat(json)
                .contains("\"id\":1")
                .contains("\"name\":\"KNIFE\"")
                .contains("\"description\":\"Hallow WORLD\"")
                .contains("\"available\":true")
                .contains("\"requestId\":1");
    }


    @Test
    void testSerializableNotEqualsRequestId() throws JsonProcessingException {
        ItemDto itemDto = new ItemDto();

        itemDto.setId(1L);
        itemDto.setName("KNIFE");
        itemDto.setDescription("Hallow WORLD");
        itemDto.setAvailable(true);

        String json = objectMapper.writeValueAsString(itemDto);

        assertThat(json)
                .contains("\"id\":1")
                .contains("\"name\":\"KNIFE\"")
                .contains("\"description\":\"Hallow WORLD\"")
                .contains("\"available\":true");
    }

    @Test
    void testItemDtoDeSerializableEqualsRequestId() throws JsonProcessingException {
        String json = "{\"id\":1,\"name\":\"KNIFE\",\"description\":\"Hallow WORLD\",\"available\":true,\"requestId\":1}";

        ItemDto itemDto = objectMapper.readValue(json, ItemDto.class);

        assertThat(itemDto.getId()).isEqualTo(1L);
        assertThat(itemDto.getName()).isEqualTo("KNIFE");
        assertThat(itemDto.getDescription()).isEqualTo("Hallow WORLD");
        assertThat(itemDto.getAvailable()).isEqualTo(true);
        assertThat(itemDto.getRequestId()).isEqualTo(1L);
    }

    @Test
    void testItemDtoDeSerializableNotEqualsRequestId() throws JsonProcessingException {
        String json = "{\"id\":1,\"name\":\"KNIFE\",\"description\":\"Hallow WORLD\",\"available\":true}";

        ItemDto itemDto = objectMapper.readValue(json, ItemDto.class);

        assertThat(itemDto.getId()).isEqualTo(1L);
        assertThat(itemDto.getName()).isEqualTo("KNIFE");
        assertThat(itemDto.getDescription()).isEqualTo("Hallow WORLD");
        assertThat(itemDto.getAvailable()).isEqualTo(true);
    }

}