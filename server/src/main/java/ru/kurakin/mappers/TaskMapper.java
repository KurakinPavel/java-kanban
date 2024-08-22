package ru.kurakin.mappers;

import ru.kurakin.dto.task.FullTaskDto;
import ru.kurakin.dto.task.NewTaskDto;
import ru.kurakin.dto.task.ShortTaskDto;
import ru.kurakin.enums.TaskStatus;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class TaskMapper {
    public static Task toTask(NewTaskDto newTaskDto, Epic epic, User performer, Set<Task> dependentTasks) {
        return new Task(
                0, epic, performer, dependentTasks,
                newTaskDto.getTitle() != null ? newTaskDto.getTitle() : "",
                newTaskDto.getDescription() != null ? newTaskDto.getDescription() : "",
                TaskStatus.NEW,
                newTaskDto.getDuration() != null ? newTaskDto.getDuration() : 0,
                newTaskDto.getStartDate() != null ? newTaskDto.getStartDate() : null,
                newTaskDto.getStartDate() != null ? newTaskDto.getStartDate().plusDays(newTaskDto.getDuration() != null
                        ? newTaskDto.getDuration() : 0) : null
        );
    }

    public static FullTaskDto toFullTaskDto(Task task) {
        return new FullTaskDto(
                task.getId(),
                EpicMapper.toShortEpicDto(task.getEpic()),
                UserMapper.toShortUserDto(task.getPerformer()),
                task.getDependentTasks().stream()
                        .map(TaskMapper::toShortTaskDto)
                        .collect(Collectors.toList()),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().toString(),
                task.getDuration(),
                task.getStartDate().toString(),
                task.getEndDate().toString()
        );
    }

    public static ShortTaskDto toShortTaskDto(Task task) {
        return new ShortTaskDto(
                task.getId(),
                UserMapper.toShortUserDto(task.getPerformer()),
                task.getTitle(),
                task.getStatus().toString(),
                task.getStartDate().toString(),
                task.getEndDate().toString()
        );
    }
}
