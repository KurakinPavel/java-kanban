package ru.kurakin.mappers;

import ru.kurakin.dto.NewTaskDto;
import ru.kurakin.dto.TaskStatus;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;

import java.util.Set;

public class TaskMapper {
    public static Task toTask(NewTaskDto newTaskDto, Epic epic, Set<User> performers) {
        return new Task(
                0, epic, performers,
                newTaskDto.getTitle() != null ? newTaskDto.getTitle() : "",
                newTaskDto.getDescription() != null ? newTaskDto.getDescription() : "",
                TaskStatus.NEW,
                newTaskDto.getDuration() != null ? newTaskDto.getDuration() : 0,
                newTaskDto.getStartDate() != null ? newTaskDto.getStartDate() : null,
                newTaskDto.getStartDate() != null ? newTaskDto.getStartDate().plusDays(newTaskDto.getDuration() != null
                        ? newTaskDto.getDuration() : 0) : null
        );
    }



}
