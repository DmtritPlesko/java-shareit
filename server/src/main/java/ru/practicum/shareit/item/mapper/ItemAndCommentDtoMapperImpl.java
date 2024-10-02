package ru.practicum.shareit.item.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemAndCommentDto;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemAndCommentDtoMapperImpl implements ItemAndCommentDtoMapper {
    @Override
    public ItemAndCommentDto parseItemInItemAndCommentDto(Item item) {

        ItemAndCommentDto itemAndCommentDto = new ItemAndCommentDto(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getOwner());

        if (item.getRequest() != null) {
            itemAndCommentDto.setRequest(item.getRequest());
        }

        return itemAndCommentDto;
    }

    @Override
    public Item parseItemAndCommentDtoInItem(ItemAndCommentDto itemAndCommentDto) {
        Item item = new Item();

        item.setId(itemAndCommentDto.getId());
        item.setName(itemAndCommentDto.getName());
        item.setDescription(itemAndCommentDto.getDescription());
        item.setAvailable(itemAndCommentDto.getAvailable());
        item.setOwner(itemAndCommentDto.getOwner());

        if (itemAndCommentDto.getRequest() != null) {
            item.setRequest(itemAndCommentDto.getRequest());
        }

        return item;
    }
}
