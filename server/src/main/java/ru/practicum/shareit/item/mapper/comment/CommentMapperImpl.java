package ru.practicum.shareit.item.mapper.comment;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentDto parseCommentInCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(), comment.getItem(),
                comment.getAuthorId().getName(), comment.getCreated());
    }

    @Override
    public Comment parseCommentDtoInComment(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setItem(commentDto.getItem());
        comment.setCreated(commentDto.getCreated());

        return comment;
    }
}
