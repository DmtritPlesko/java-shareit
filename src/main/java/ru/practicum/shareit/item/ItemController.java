package ru.practicum.shareit.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemAndCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.item.ItemMapper;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    public ItemDto addNewItem(@RequestBody ItemDto item,
                              @RequestHeader("X-Sharer-User-Id") Long ownerId) {

        return itemService.addNewItem(item, ownerId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createNewComment(@PathVariable("itemId") Long itemId,
                                       @RequestBody CommentDto commentDto,
                                       @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.addNewComment(itemId, commentDto, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable("itemId") @Positive Long itemId,
                              @RequestBody ItemDto item,
                              @RequestHeader("X-Sharer-User-Id") @NotNull Long ownerId) {

        return itemService.updateItem(itemId, item, ownerId);
    }

    @GetMapping("/{itemId}")
    public ItemAndCommentDto getItemBuId(@PathVariable("itemId") Long itemId,
                                         @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping
    public List<ItemDto> getItemsBuOwnerId(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.getItemsByUserId(ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text,
                             @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.search(text, ownerId);
    }
}
