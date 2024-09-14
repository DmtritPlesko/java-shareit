package ru.practicum.shareit.item.service;


import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemAndCommentDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;


public interface ItemService {

    Item addNewItem(Item item, Long ownerId);

    CommentDto addNewComment(Long itemId, CommentDto commentDto, Long userId);

    Item updateItem(Long itemId, Item item, Long ownerId);

    List<Item> getItemsByUserId(Long userId);

    ItemAndCommentDto getItemById(Long itemId);

    List<Item> search(String text, Long ownerId);

}