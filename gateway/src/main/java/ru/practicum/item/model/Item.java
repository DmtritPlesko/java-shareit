package ru.practicum.item.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.practicum.request.model.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Entity
@Table(name = "items")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"id"})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "IS_AVAILABLE")
    Boolean available;

    @Column(name = "OWNER_ID")
    Long owner;

    @ManyToOne
    @JoinColumn(name = "REQUEST_ID")
    ItemRequest request;
}
