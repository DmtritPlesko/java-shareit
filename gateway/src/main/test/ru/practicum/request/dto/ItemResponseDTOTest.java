package ru.practicum.request.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ContextConfiguration(classes = {ItemResponseDTO.class})
class ItemResponseDTOTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializable() throws JsonProcessingException {

        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();

        itemResponseDTO.setItemId(1L);
        itemResponseDTO.setItemOwnerId(2L);
        itemResponseDTO.setItemName("Jim");

        String json = objectMapper.writeValueAsString(itemResponseDTO);

        assertThat(json)
                .contains("\"itemId\":1")
                .contains("\"itemName\":\"Jim\"")
                .contains("\"itemOwnerId\":2");

    }

    @Test
    void testDeSerializable() throws JsonProcessingException {
        String json = "{\"itemId\":1,\"itemName\":\"Jim\",\"itemOwnerId\":2}";

        ItemResponseDTO itemResponseDTO = objectMapper.readValue(json, ItemResponseDTO.class);

        assertThat(itemResponseDTO.getItemId()).isEqualTo(1);
        assertThat(itemResponseDTO.getItemName()).isEqualTo("Jim");
        assertThat(itemResponseDTO.getItemOwnerId()).isEqualTo(2);
    }
}