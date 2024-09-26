package ru.kurakin.mappers;

import ru.kurakin.dto.comment.EpicCommentDtoOut;
import ru.kurakin.dto.comment.TaskCommentDtoOut;
import ru.kurakin.dto.comment.NewCommentDto;
import ru.kurakin.model.EpicComment;
import ru.kurakin.model.TaskComment;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;

import java.time.LocalDate;

public class CommentMapper {
    public static TaskComment toTaskComment(NewCommentDto newCommentDto, Task task, User author) {
        return new TaskComment(
                0,
                newCommentDto.getComment(),
                task, author, LocalDate.now()
        );
    }

    public static EpicComment toEpicComment(NewCommentDto newCommentDto, Epic epic, User author) {
        return new EpicComment(
                0,
                newCommentDto.getComment(),
                epic, author, LocalDate.now()
        );
    }

    public static TaskCommentDtoOut toTaskCommentDtoOut(TaskComment taskComment) {
        return new TaskCommentDtoOut(
                taskComment.getId(),
                taskComment.getComment(),
                TaskMapper.toSuperShortTaskDto(taskComment.getTask()),
                UserMapper.toShortUserDto(taskComment.getAuthor()),
                taskComment.getCreated()
        );
    }

    public static EpicCommentDtoOut toEpicCommentDtoOut(EpicComment epicComment) {
        return new EpicCommentDtoOut(
                epicComment.getId(),
                epicComment.getComment(),
                EpicMapper.toSuperShortEpicDto(epicComment.getEpic()),
                UserMapper.toShortUserDto(epicComment.getAuthor()),
                epicComment.getCreated()
        );
    }
}
