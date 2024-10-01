package ru.practicum.item.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "TEXT")
    String text;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    Item item;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    User authorId;

    @Column(name = "CREATED")
    LocalDateTime created;
}
