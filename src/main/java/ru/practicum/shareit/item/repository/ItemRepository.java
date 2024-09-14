package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {

    ItemDto addNewItem(ItemDto item, Long ownerId);

    ItemDto updateItem(Long itemId, ItemDto item, Long ownerId);

    List<Item> getItemsByOwnerId(Long userId);

    Item getItemById(Long itemId);

    List<Item> search(String text, Long ownerId);
}