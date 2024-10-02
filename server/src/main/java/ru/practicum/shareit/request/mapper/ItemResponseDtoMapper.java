package ru.practicum.shareit.request.mapper;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemResponseDto;

public interface ItemResponseDtoMapper {

    ItemResponseDto parseItemInItemResponseDto(Item item);
}
