package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.List;

public interface ItemRequestService {

    ItemRequestDto addRequest(Long userId, ItemRequestDto itemRequestDto);

    List<ItemRequestDto> getRequestsByUserId(Long userId);

    List<ItemRequestDto> getAllRequests();

    ItemRequestDto getRequestBuRequestId(Long requestId);

    ItemRequest getRequestById(Long requestId);
}
