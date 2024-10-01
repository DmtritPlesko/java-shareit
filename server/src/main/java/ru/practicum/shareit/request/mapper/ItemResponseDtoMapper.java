package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemResponseDto;

@Component
public interface ItemResponseDtoMapper {

    ItemResponseDto parseItemInItemResponseDto(Item item);
}
