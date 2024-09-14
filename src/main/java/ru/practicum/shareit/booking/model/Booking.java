package ru.practicum.shareit.booking.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.status.Status;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
@Entity
@Table(name = "bookings")
@NonNull
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "START_DATE")
    LocalDateTime start;

    @Column(name = "END_DATE")
    LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    Item item;

    @ManyToOne
    @JoinColumn(name = "BOOKER_ID")
    User booker;

    @Column(name = "STATUS")
    Status status;

}
