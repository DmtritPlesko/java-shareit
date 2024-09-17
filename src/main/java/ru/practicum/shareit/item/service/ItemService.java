package ru.practicum.shareit.item.service;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemAndCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import java.util.List;


public interface ItemService {

    ItemDto addNewItem(ItemDto item, Long ownerId);

    CommentDto addNewComment(Long itemId, CommentDto commentDto, Long userId);

    ItemDto updateItem(Long itemId, ItemDto item, Long ownerId);

    List<ItemDto> getItemsByUserId(Long userId);

    ItemAndCommentDto getItemById(Long itemId);

    List<ItemDto> search(String text, Long ownerId);

}