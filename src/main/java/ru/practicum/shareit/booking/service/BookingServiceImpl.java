package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.mapper.ItemAndCommentDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.status.Status;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final BookingMapper bookingMapper;
    private final ItemAndCommentDtoMapper itemAndCommentDtoMapper;

    @Override
    public Booking addBooking(BookingDto bookingDto, Long ownerId) {
        log.info("Добавление информации о бронировании");
        System.out.println(bookingDto);

        Booking booking = bookingMapper.parseBookingDtoInBooking(bookingDto);

        User user =  userService.getUserById(ownerId);
        Item item = itemAndCommentDtoMapper
                .parseItemAndCommentDtoInItem(itemService.getItemById(bookingDto.getItemId()));

        booking.setBooker(user);
        booking.setItem(item);

        validate(booking, ownerId);
        booking.setStatus(Status.WAITING);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId, Boolean approved, Long ownerId) {
        log.info("Обновление информации о брониировании");

        Booking booking = getBookingById(bookingId, ownerId);
        if (!booking.getItem().getOwner().equals(ownerId)) {
            throw new IllegalArgumentException("Пользователь с id = " + ownerId + " не явлется владельцем вещи");
        }
        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);

        return booking;
    }

    @Override
    public void deleteBookingById(Long bookingId) {
        log.info("Удаление бронирования по его Id = {} ", bookingId);
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public Booking getBookingById(Long bookingId, Long ownerId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("Бронировнаие не наейдено"));
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll().stream()
                .sorted(Comparator.comparing(Booking::getStart).reversed())
                .toList();
    }

    @Override
    public List<Booking> getAllBookingByUserId(Long userId, String state) {

        switch (state) {
            case "CURRENT":
            case "FUTURE": {
                return bookingRepository.findBookingsByUserAndStatus(userId, Status.APPROVED);
            }
            case "PAST": {
                return bookingRepository.findBookingsByUserAndStatus(userId, Status.CANCELED);
            }
            case "WAITING": {
                return bookingRepository.findBookingsByUserAndStatus(userId, Status.WAITING);
            }
            case "REJECTED": {
                return bookingRepository.findBookingsByUserAndStatus(userId, Status.REJECTED);

            }
            default: {
                return bookingRepository.findBookingsByUserAndStatus(userId, Status.WAITING);
            }
        }
    }

    @Override
    public List<Booking> getAllUsersItemBookings(Long ownerId, Status state) {
        return bookingRepository.findAll().stream()
                .filter(elem -> Objects.equals(elem.getItem().getId(), ownerId))
                .filter(elem -> Objects.equals(elem.getStatus(), state))
                .toList();
    }

    private void validate(Booking booking, Long ownerId) {

        if (booking.getStart() == null) {
            throw new ValidationException("Дата начала не может быть равна null");
        }
        if (booking.getEnd() == null) {
            throw new ValidationException("Дата конца не может быть равна null");
        }
        if (booking.getStart().equals(booking.getEnd())) {
            throw new ValidationException("Даты не могут быть равны");
        }
        if (booking.getEnd().isBefore(booking.getStart())) {
            throw new ValidationException("Дата конца не может быть раньше даты начала ");
        }
        if (!itemService.getItemById(booking.getItem().getId()).getAvailable()) {
            throw new ValidationException("Предмет не найден");
        }
    }
}
