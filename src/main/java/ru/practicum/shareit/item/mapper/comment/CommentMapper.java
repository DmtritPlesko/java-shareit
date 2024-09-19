package ru.practicum.shareit.item.mapper.comment;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;

public interface CommentMapper {
    CommentDto parseCommentInCommentDto(Comment comment);

    Comment parseCommentDtoInComment(CommentDto commentDto);
}
