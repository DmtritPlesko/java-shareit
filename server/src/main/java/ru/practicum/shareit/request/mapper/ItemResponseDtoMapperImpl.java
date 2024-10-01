package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemResponseDTO;

@Component
public class ItemResponseDtoMapperImpl implements ItemResponseDtoMapper {


    @Override
    public ItemResponseDTO parseItemInItemResponseDto(Item item) {
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();

        itemResponseDTO.setId(item.getId());
        itemResponseDTO.setName(item.getName());
        itemResponseDTO.setOwnerId(item.getOwner());

        return itemResponseDTO;
    }
}
