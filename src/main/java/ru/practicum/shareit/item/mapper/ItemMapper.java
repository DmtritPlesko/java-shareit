package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

public interface ItemMapper {

    Item parseItemDtoInItem(ItemDto itemDto);

    ItemDto parseItemInItemDto(Item item);
}
