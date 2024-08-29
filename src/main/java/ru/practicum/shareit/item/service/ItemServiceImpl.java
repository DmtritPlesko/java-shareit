package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Override
    public Item addNewItem(ItemDto item, Long ownerId) {
        validationItem(item, ownerId);

        return itemRepository.addNewItem(item, ownerId);
    }

    @Override
    public Item updateItem(Long itemId, ItemDto item, Long ownerId) {
        checkExistOwner(ownerId);
        return itemRepository.updateItem(itemId, item, ownerId);
    }

    @Override
    public List<Item> getItemsBuUserId(Long userId) {
        return itemRepository.getItemsBuOwnerId(userId);
    }

    @Override
    public Item getItemBuId(Long itemId) {
        return itemRepository.getItemBuId(itemId);
    }

    @Override
    public List<Item> search(String text, Long ownerId) {
        return itemRepository.search(text, ownerId);
    }

    private void validationItem(ItemDto item, Long ownerId) {
        String starMessage = "Ошибка валидации: ";

        if (item.getName() == null) {
            log.error("Название не может быть пустым");
            throw new ValidationException(starMessage + "Название не может быть пустым");
        } else if (item.getName().isBlank()) {
            log.error("Название предмета не может состоять из пробелов");
            throw new ValidationException(starMessage + "Название предмета не может состоять из пробелов");
        } else if (item.getDescription() == null) {
            log.error("Описание предемета не моежет быть пустым");
            throw new ValidationException(starMessage + "Описание предемета не моежет быть пустым");
        } else if (item.getDescription().isBlank()) {
            log.error("Описание предмета не может состоять из пробелов");
            throw new ValidationException(starMessage + "Описание предмета не может состоять из пробелов");
        } else if (ownerId == null) {
            log.error("Айди владельца не может быть пустым");
            throw new ValidationException(starMessage + "Айди владельца не может быть пустым");
        } else if (item.getAvailable() == null) {
            log.error("Статуст должен быть заполнен");
            throw new ValidationException(starMessage + "Статуст должен быть заполнен");
        }
        checkExistOwner(ownerId);

    }

    private void checkExistOwner(Long ownerId) {
        if (Objects.isNull(userService.getUserBuId(ownerId))) {
            log.error("Пользователь с id = " + ownerId + " не найден");
            throw new NotFoundException("Пользователь с id = " + ownerId + " не найден");
        }
    }
}