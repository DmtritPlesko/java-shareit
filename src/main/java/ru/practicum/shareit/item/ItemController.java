package ru.practicum.shareit.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
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

    @PostMapping
    public ItemDto addNewItem(@RequestBody ItemDto item,
                           @RequestHeader("X-Sharer-User-Id") Long ownerId) {

        return itemService.addNewItem(item, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable("itemId") @Positive Long itemId,
                           @RequestBody ItemDto item,
                           @RequestHeader("X-Sharer-User-Id") @NotNull Long ownerId) {
        return itemService.updateItem(itemId, item, ownerId);
    }

    @GetMapping("/{itemId}")
    public Item getItemBuId(@PathVariable("itemId") Long itemId,
                            @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping
    public List<Item> getItemsBuOwnerId(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.getItemsByUserId(ownerId);
    }

    @GetMapping("/search")
    public List<Item> search(@RequestParam String text,
                             @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.search(text, ownerId);
    }
}
