package ru.kurakin.mappers;

import ru.kurakin.dto.epic.FullEpicDto;
import ru.kurakin.dto.epic.NewEpicDto;
import ru.kurakin.dto.epic.ShortEpicDto;
import ru.kurakin.enums.TaskStatus;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class EpicMapper {
    public static Epic toEpic(NewEpicDto newEpicDto, User coordinator, Set<Task> tasks, TaskStatus status,
                              int duration, LocalDate startTime, LocalDate endTime) {
        return new Epic(
                0,
                coordinator, tasks,
                newEpicDto.getTitle() != null ? newEpicDto.getTitle() : "",
                newEpicDto.getDescription() != null ? newEpicDto.getDescription() : "",
                status, duration, startTime, endTime
        );
    }

    public static FullEpicDto toFullEpicDto(Epic epic) {
        return new FullEpicDto(
                epic.getId(),
                UserMapper.toShortUserDto(epic.getCoordinator()),
                epic.getTasks().stream()
                        .map(TaskMapper::toFullTaskDto)
                        .collect(Collectors.toList()),
                epic.getTitle(),
                epic.getDescription(),
                epic.getStatus().toString(),
                epic.getDuration(),
                epic.getStartTime().toString(),
                epic.getEndTime().toString()
        );
    }

    public static ShortEpicDto toShortEpicDto(Epic epic) {
        return new ShortEpicDto(
                epic.getId(),
                UserMapper.toShortUserDto(epic.getCoordinator()),
                epic.getTasks().stream()
                        .map(TaskMapper::toShortTaskDto)
                        .collect(Collectors.toList()),
                epic.getTitle(),
                epic.getStatus().toString(),
                epic.getStartTime().toString(),
                epic.getEndTime().toString()
        );
    }
}
