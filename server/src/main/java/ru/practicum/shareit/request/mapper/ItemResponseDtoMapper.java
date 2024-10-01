package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemResponseDTO;

@Component
public interface ItemResponseDtoMapper {

    ItemResponseDTO parseItemInItemResponseDto(Item item);
}
