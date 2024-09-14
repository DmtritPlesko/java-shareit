package ru.practicum.shareit.item.mapper;


import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item parseItemDtoInItem(ItemDto itemDto) {
        Item item = new Item();

        item.setDescription(itemDto.getDescription());
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());

        if (itemDto.getAvailable() != null) {

            item.setAvailable(itemDto.getAvailable());

        }

        return item;
    }

    @Override
    public ItemDto parseItemInItemDto(Item item) {

        ItemDto itemDto = new ItemDto();

        itemDto.setId(item.getId());
        itemDto.setDescription(item.getDescription());
        itemDto.setName(item.getName());
        itemDto.setAvailable(item.getAvailable());

        return itemDto;

    }
}
