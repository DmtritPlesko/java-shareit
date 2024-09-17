package ru.practicum.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.status.Status;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT DISTINCT b FROM Booking b " +
            "JOIN FETCH b.item i " +
            "JOIN FETCH b.booker u " +
            "WHERE b.booker.id = :userId " +
            "AND (:status IS NULL OR b.status = :status)")
    List<Booking> findBookingsByUserAndStatus(@Param("userId") Long userId,
                                              @Param("status") Status status);


}