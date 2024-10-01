package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.item.dto.ItemAndCommentDto;
import ru.practicum.shareit.item.model.Item;

public interface ItemAndCommentDtoMapper {
    ItemAndCommentDto parseItemInItemAndCommentDto(Item item);

    Item parseItemAndCommentDtoInItem(ItemAndCommentDto itemAndCommentDto);
}
