package ru.practicum.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.item.dto.CommentDto;
import ru.practicum.item.dto.ItemDto;


@RestController
@RequestMapping(path = "/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemClient itemClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader("X-Sharer-User-Id") Long userId,
                                         @RequestBody ItemDto itemDto) {
        return itemClient.create(userId, itemDto);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> createNewComment(@PathVariable("itemId") Long itemId,
                                                   @RequestBody CommentDto commentDto,
                                                   @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemClient.createNewComment(itemId, commentDto, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> updateItem(@PathVariable("itemId") @Positive Long itemId,
                                             @RequestBody ItemDto item,
                                             @RequestHeader("X-Sharer-User-Id") @NotNull Long ownerId) {
        return itemClient.update(itemId, item, ownerId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItemBuId(@PathVariable("itemId") Long itemId,
                                              @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemClient.getItemById(itemId, ownerId);
    }

    @GetMapping
    public ResponseEntity<Object> getItemsBuOwnerId(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemClient.getItemsBuOwnerId(ownerId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String text,
                                         @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemClient.search(text, ownerId);
    }
}
