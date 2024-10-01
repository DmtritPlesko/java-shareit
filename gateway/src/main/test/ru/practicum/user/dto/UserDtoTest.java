package ru.practicum.user.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ContextConfiguration(classes = UserDto.class)
class UserDtoTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializable() throws JsonProcessingException {

        UserDto userDto = new UserDto();

        userDto.setId(1L);
        userDto.setName("Jim");
        userDto.setEmail("JimJim@mail.ru");

        String json = objectMapper.writeValueAsString(userDto);

        assertThat(json)
                .contains("\"id\":1")
                .contains("\"name\":\"Jim\"")
                .contains("\"email\":\"JimJim@mail.ru\"");
    }

    @Test
    void testDeSerializable() throws JsonProcessingException {

        String json = "{\"id\":1,\"name\":\"Jim\",\"email\":\"JimJim@mail.ru\"}";

        UserDto userDto = objectMapper.readValue(json, UserDto.class);

        assertThat(userDto.getId()).isEqualTo(1L);
        assertThat(userDto.getName()).isEqualTo("Jim");
        assertThat(userDto.getEmail()).isEqualTo("JimJim@mail.ru");
    }

}