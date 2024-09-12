package ru.kurakin.mappers;

import ru.kurakin.dto.comment.TaskCommentDtoOut;
import ru.kurakin.dto.comment.NewCommentDto;
import ru.kurakin.model.TaskComment;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;

import java.time.LocalDate;

public class CommentMapper {
    public static TaskComment toComment(NewCommentDto newCommentDto, Epic epic, Task task, User author) {
        return new TaskComment(
                0,
                newCommentDto.getComment(),
                epic, task, author, LocalDate.now()
        );
    }

    public static TaskCommentDtoOut toCommentDtoOut(TaskComment taskComment) {
        return new TaskCommentDtoOut(
                taskComment.getId(),
                taskComment.getComment(),
                taskComment.getEpic() != null ? EpicMapper.toSuperShortEpicDto(taskComment.getEpic()) : null,
                taskComment.getTask() != null ? TaskMapper.toSuperShortTaskDto(taskComment.getTask()) : null,
                UserMapper.toShortUserDto(taskComment.getAuthor()),
                taskComment.getCreated()
        );
    }
}
