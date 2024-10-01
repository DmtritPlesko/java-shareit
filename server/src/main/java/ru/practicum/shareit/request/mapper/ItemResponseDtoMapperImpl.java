package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemResponseDto;

@Component
public class ItemResponseDtoMapperImpl implements ItemResponseDtoMapper {


    @Override
    public ItemResponseDto parseItemInItemResponseDto(Item item) {
        ItemResponseDto itemResponseDTO = new ItemResponseDto();

        itemResponseDTO.setId(item.getId());
        itemResponseDTO.setName(item.getName());
        itemResponseDTO.setOwnerId(item.getOwner());

        return itemResponseDTO;
    }
}
