package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemAndCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemAndCommentDtoMapperImpl;
import ru.practicum.shareit.item.mapper.comment.CommentMapper;
import ru.practicum.shareit.item.mapper.item.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.CommentRepository;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.service.ItemRequestService;
import ru.practicum.shareit.user.service.UserService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CommentRepository commentRepository;
    private final BookingRepository bookingRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final ItemRequestService itemRequestService;
    private final ItemAndCommentDtoMapperImpl itemAndCommentDtoMapper;

    @Override
    public ItemDto addNewItem(ItemDto item, Long ownerId) {
        validationItem(item, ownerId);
        log.info("Добавление новой вещи");
        Item item1 = itemMapper.parseItemDtoInItem(item);
        item1.setOwner(ownerId);

        if (item.getRequestId() != null) {
            item1.setRequest(itemRequestService.getRequestById(item.getRequestId()));
        }

        itemRepository.save(item1);


        return itemMapper.parseItemInItemDto(item1);
    }

    @Override
    public CommentDto addNewComment(Long itemId, CommentDto commentDto, Long userId) {

        Optional<Booking> optionalBooking = bookingRepository.findAll().stream()
                .filter(elem -> Objects.equals(elem.getItem().getId(), itemId))
                .findFirst();

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Astrakhan"));
        commentDto.setCreated(zdt.toLocalDateTime());
        commentDto.setAuthorName(userService.getUserById(userId).getName());
        commentDto.setItem(optionalBooking.get().getItem());
        commentDto.setAuthorName(userService.getUserById(userId).getName());


        System.out.println("qowpkfoqwkfopkqwpofkqwkp" + commentDto + "BOOKING" + optionalBooking);

        if (optionalBooking.get().getBooker().getId().equals(userId) &&
                optionalBooking.get().getEnd().isBefore(commentDto.getCreated())) {
            commentRepository.save(commentMapper.parseCommentDtoInComment(commentDto));
            return commentDto;
        }

        throw new ValidationException("Невозможно добавить комментарий до окончания срока аренды");
    }

    @Override
    public ItemDto updateItem(Long itemId, ItemDto item, Long ownerId) {
        checkExistOwner(ownerId);

        Optional<Item> item1 = itemRepository.findById(itemId);

        item1.get().setDescription(item.getDescription());
        item1.get().setName(item.getName());
        item1.get().setAvailable(item.getAvailable());
        item1.get().setOwner(ownerId);

        log.info("Обновление вещи с id = {}", itemId);
        itemRepository.save(item1.get());

        return itemMapper.parseItemInItemDto(item1.get());
    }

    @Override
    public List<ItemDto> getItemsByUserId(Long userId) {
        log.info("Полечение всех вещей пользователя с id = {}", userId);
        userService.getUserById(userId);
        return itemRepository.findAll().stream()
                .filter(elem -> Objects.equals(elem.getOwner(), userId))
                .map(itemMapper::parseItemInItemDto)
                .toList();
    }

    @Override
    public ItemAndCommentDto getItemById(Long itemId) {
        log.info("Получения вещи с id = {}", itemId);
        ItemAndCommentDto itemAndCommentDto = itemAndCommentDtoMapper
                .parseItemInItemAndCommentDto(itemRepository.findById(itemId)
                        .orElseThrow(() -> new NotFoundException("Предмет не найден")));

        itemAndCommentDto.setComments(commentRepository.findAll().stream()
                .filter(elem -> Objects.equals(elem.getItem().getId(), itemId))
                .toList());
        return itemAndCommentDto;
    }

    @Override
    public List<ItemDto> search(String text, Long ownerId) {
        log.info("Поиск вещи по имени или описанию в которых содержится строка = {}", text);
        if (text == null || text.isEmpty() || text.isBlank()) {
            return List.of();
        }
        return itemRepository.search(text, ownerId).stream()
                .filter(elem -> Objects.equals(elem.getAvailable(), true))
                .map(itemMapper::parseItemInItemDto)
                .toList();
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
            log.error("Описание предмета не может быть пустым");
            throw new ValidationException(starMessage + "Описание предмета не может быть пустым");
        } else if (item.getDescription().isBlank()) {
            log.error("Описание предмета не может состоять из пробелов");
            throw new ValidationException(starMessage + "Описание предмета не может состоять из пробелов");
        } else if (ownerId == null) {
            log.error("Айди владельца не может быть пустым");
            throw new ValidationException(starMessage + "Айди владельца не может быть пустым");
        } else if (item.getAvailable() == null) {
            log.error("Статус должен быть заполнен");
            throw new ValidationException(starMessage + "Статус должен быть заполнен");
        }
        checkExistOwner(ownerId);

    }

    private void checkExistOwner(Long ownerId) {
        if (Objects.isNull(userService.getUserById(ownerId))) {
            log.error("Пользователь с id = " + ownerId + " не найден");
            throw new NotFoundException("Пользователь с id = " + ownerId + " не найден");
        }
    }
}