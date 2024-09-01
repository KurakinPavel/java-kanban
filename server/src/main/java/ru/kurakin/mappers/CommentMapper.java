package ru.kurakin.mappers;

import ru.kurakin.dto.comment.CommentDtoOut;
import ru.kurakin.dto.comment.NewCommentDto;
import ru.kurakin.model.Comment;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;

import java.time.LocalDate;

public class CommentMapper {
    public static Comment toComment(NewCommentDto newCommentDto, Epic epic, Task task, User author) {
        return new Comment(
                0,
                newCommentDto.getComment(),
                epic, task, author, LocalDate.now()
        );
    }

    public static CommentDtoOut toCommentDtoOut(Comment comment) {
        return new CommentDtoOut(
                comment.getId(),
                comment.getComment(),
                comment.getEpic() != null ? EpicMapper.toSuperShortEpicDto(comment.getEpic()) : null,
                comment.getTask() != null ? TaskMapper.toSuperShortTaskDto(comment.getTask()) : null,
                UserMapper.toShortUserDto(comment.getAuthor()),
                comment.getCreated()
        );
    }
}
