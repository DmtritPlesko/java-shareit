package ru.practicum.shareit.item.service;


import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;


public interface ItemService {

    ItemDto addNewItem(ItemDto item, Long ownerId);

    ItemDto updateItem(Long itemId, ItemDto item, Long ownerId);

    List<Item> getItemsByUserId(Long userId);

    Item getItemById(Long itemId);

    List<Item> search(String text, Long ownerId);
}