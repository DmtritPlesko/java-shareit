package ru.practicum.shareit.item.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemAndCommentDto;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemAndCommentDtoMapperImpl implements ItemAndCommentDtoMapper {
    @Override
    public ItemAndCommentDto parseItemInItemAndCommentDto(Item item) {
        return new ItemAndCommentDto(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getOwner(),
                item.getRequest());
    }

    @Override
    public Item parseItemAndCommentDtoInItem(ItemAndCommentDto itemAndCommentDto) {
        Item item = new Item();

        item.setId(itemAndCommentDto.getId());
        item.setName(itemAndCommentDto.getName());
        item.setDescription(itemAndCommentDto.getDescription());
        item.setAvailable(itemAndCommentDto.getAvailable());
        item.setOwner(itemAndCommentDto.getOwner());
        item.setRequest(itemAndCommentDto.getRequest());

        return item;
    }
}
