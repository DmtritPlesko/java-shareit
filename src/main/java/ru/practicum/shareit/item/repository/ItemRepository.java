package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {

    Item addNewItem(ItemDto item, Long ownerId);

    Item updateItem(Long itemId, ItemDto item, Long ownerId);

    List<Item> getItemsBuOwnerId(Long userId);

    Item getItemBuId(Long itemId);

    List<Item> search(String text, Long ownerId);
}