package ru.practicum.request.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
@ContextConfiguration(classes = {ItemRequestDto.class})
class ItemRequestDtoTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializable() throws JsonProcessingException {

        LocalDateTime time = LocalDateTime.of(2024, 9, 12, 12, 24);

        ItemRequestDto itemRequestDto = new ItemRequestDto();

        itemRequestDto.setId(1L);
        itemRequestDto.setDescription("HALLOW");
        itemRequestDto.setCreated(time);
        itemRequestDto.setItemResponseDTOList(new ArrayList<>());

        String json = objectMapper.writeValueAsString(itemRequestDto);

        assertThat(json)
                .contains("\"id\":1")
                .contains("\"description\":\"HALLOW\"")
                .contains("\"created\":\"2024-09-12T12:24:00")
                .contains("\"itemResponseDTOList\":[]");


    }

    @Test
    void testDeSerializable() throws JsonProcessingException {
        String json = "{\"id\":1,\"description\":\"HALLOW\",\"created\":\"2024-09-12T12:24:00\",\"itemResponseDTOList\":[]}";


        ItemRequestDto itemRequestDto = objectMapper.readValue(json, ItemRequestDto.class);

        assertThat(itemRequestDto.getId()).isEqualTo(1L);
        assertThat(itemRequestDto.getDescription()).isEqualTo("HALLOW");
        assertThat(itemRequestDto.getCreated()).isEqualTo(LocalDateTime.of(2024, 9, 12, 12, 24));
        assertThat(itemRequestDto.getItemResponseDTOList()).isEmpty();
    }

}