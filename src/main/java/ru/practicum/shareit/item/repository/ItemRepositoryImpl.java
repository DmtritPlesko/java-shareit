package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;

import java.util.HashMap;
import java.util.List;

import ru.practicum.shareit.item.model.Item;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> itemMap = new HashMap<>();
    private final ItemMapper itemMapper;

    @Override
    public ItemDto addNewItem(ItemDto item, Long ownerId) {
        item.setId(nextValue());
        Item item1 = itemMapper.parseItemDtoInItem(item);
        item1.setOwner(ownerId);
        itemMap.put(item1.getId(), item1);
        log.info("Добавление предмета id = {} name = {}", item1.getId(), item1.getName());
        return itemMapper.parseItemInItemDto(item1);
    }

    @Override
    public ItemDto updateItem(Long itemId, ItemDto item, Long ownerId) {

        Item item1 = getItemById(itemId);

        if (Objects.nonNull(item.getName())) {
            item1.setName(item.getName());
        }

        if (Objects.nonNull(item.getDescription())) {
            item1.setDescription(item.getDescription());
        }

        if (Objects.nonNull(item.getAvailable())) {
            item1.setAvailable(item.getAvailable());
        }

        log.info("Обновление предмета  с id = {}", item.getId());
        return itemMapper.parseItemInItemDto(item1);
    }

    @Override
    public List<Item> getItemsByOwnerId(Long ownerId) {
        log.info("Все предметы владельца под id = {}", ownerId);
        return itemMap.values().stream()
                .filter(elem -> Objects.equals(elem.getOwner(), ownerId))
                .toList();
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemMap.values().stream()
                .filter(elem -> Objects.equals(elem.getId(), itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неверный айди придмета"));
    }

    @Override
    public List<Item> search(String text, Long ownerId) {
        log.info("Поиск предмета со строкой - {}", text);
        return getItemsByOwnerId(ownerId).stream()
                .filter(elem -> (Pattern.matches("^" + text.toLowerCase() + "$", elem.getName().toLowerCase()) ||
                        Pattern.matches("^" + text.toLowerCase() + "$", elem.getDescription().toLowerCase())) &&
                        elem.getAvailable())
                .toList();
    }

    private long nextValue() {
        long number = itemMap.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);

        return ++number;
    }
}