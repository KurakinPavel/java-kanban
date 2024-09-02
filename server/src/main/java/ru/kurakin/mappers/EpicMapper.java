package ru.kurakin.mappers;

import ru.kurakin.dto.epic.EpicParametersDto;
import ru.kurakin.dto.epic.FullEpicDto;
import ru.kurakin.dto.epic.NewEpicDto;
import ru.kurakin.dto.epic.ShortEpicDto;
import ru.kurakin.dto.epic.SuperShortEpicDto;
import ru.kurakin.enums.TaskStatus;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
                !epic.getTasks().isEmpty() ?
                    epic.getTasks().stream()
                            .map(TaskMapper::toFullTaskDto)
                            .collect(Collectors.toList()) : new ArrayList<>(),
                epic.getTitle(),
                epic.getDescription(),
                epic.getStatus().toString(),
                epic.getDuration(),
                epic.getStartTime() != null ? epic.getStartTime().toString() : null,
                epic.getEndTime() != null ? epic.getEndTime().toString() : null
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
                epic.getStartTime() != null ? epic.getStartTime().toString() : null,
                epic.getEndTime() != null ? epic.getEndTime().toString() : null
        );
    }

    public static SuperShortEpicDto toSuperShortEpicDto(Epic epic) {
        return new SuperShortEpicDto(
                epic.getId(),
                epic.getTitle()
        );
    }

    public static EpicParametersDto calculateEpicParameters(List<Task> tasks) {
        int duration = 0;
        TaskStatus calculatedStatus = null;
        if (tasks.isEmpty()) {
            return new EpicParametersDto(0, TaskStatus.DONE, null, null);
        }
        LocalDate epicStart = tasks.get(0).getStartDate();
        LocalDate epicEnd = tasks.get(0).getEndDate();
        Set<TaskStatus> taskStats = new HashSet<>();
        for (Task task : tasks) {
            duration += task.getDuration();
            taskStats.add(task.getStatus());
            if (epicStart.isAfter(task.getStartDate())) {
                epicStart = task.getStartDate();
            }
            if (epicEnd.isBefore(task.getEndDate())) {
                epicEnd = task.getEndDate();
            }
        }
        List<TaskStatus> taskStatuses = new ArrayList<>(taskStats);
        if (taskStatuses.size() == 1) {
            calculatedStatus = taskStatuses.get(0);
        } else if (taskStatuses.size() > 1) {
            calculatedStatus = TaskStatus.IN_PROGRESS;
        }
        return new EpicParametersDto(duration, calculatedStatus, epicStart, epicEnd);
    }
}
