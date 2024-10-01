package ru.practicum.shareit.request.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemResponseDTO;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.request.mapper.ItemResponseDtoMapper;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.ItemRequestRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepository requestRepository;
    private final ItemRequestMapper itemRequestMapper;
    private final UserService userService;
    private final ItemRepository itemRepository;
    private final ItemResponseDtoMapper itemResponseDtoMapper;

    @Override
    @Transactional
    public ItemRequestDto addRequest(Long userId, ItemRequestDto itemRequestDto) {
        validation(itemRequestDto);

        User user = userService.getUserById(userId);
        ItemRequest itemRequest = itemRequestMapper.parseItemRequestDtoInItemRequest(itemRequestDto);
        itemRequest.setRequester(user);
        itemRequest.setCreated(LocalDateTime.now());

        return itemRequestMapper.parseItemRequestInItemRequestDto(requestRepository.save(itemRequest));
    }

    @Override
    public List<ItemRequestDto> getRequestsByUserId(Long userId) {

        List<ItemResponseDTO> itemResponseDTOList = itemRepository.getItemByUserId(userId).stream()
                .map(itemResponseDtoMapper::parseItemInItemResponseDto)
                .toList();

        return requestRepository.findByRequesterIdOrderByCreatedDesc(userId).stream()
                .map(itemRequestMapper::parseItemRequestInItemRequestDto)
                .peek(elem -> elem.setItems(itemResponseDTOList))
                .toList();
    }

    @Override
    public List<ItemRequestDto> getAllRequests() {
        return requestRepository.findAll().stream()
                .sorted(Comparator.comparing(ItemRequest::getCreated))
                .map(itemRequestMapper::parseItemRequestInItemRequestDto)
                .toList();
    }

    @Override
    public ItemRequestDto getRequestBuRequestId(Long requestId) {

        ItemRequestDto itemRequestDto = itemRequestMapper.parseItemRequestInItemRequestDto(getRequestById(requestId));

        List<ItemResponseDTO> itemResponseDTOList = itemRepository.getItemByRequestId(requestId).stream()
                .map(itemResponseDtoMapper::parseItemInItemResponseDto)
                .toList();

        itemRequestDto.setItems(itemResponseDTOList);

        return itemRequestDto;
    }

    @Override
    public ItemRequest getRequestById(Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("НЕТ ТАКОГО"));
    }

    private void validation(ItemRequestDto itemRequestDto) {
        if (itemRequestDto.getDescription().isEmpty()) {
            log.info("Описание не может быть пустым");
            throw new ValidationException("Описание не может быть пустым");
        } else if (itemRequestDto.getDescription().isBlank()) {
            log.info("Описание не может состоять только из пробелов");
            throw new ValidationException("Описание не может состоять только из пробелов");
        }
    }
}
