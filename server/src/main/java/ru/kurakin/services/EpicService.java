package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurakin.dto.epic.FullEpicDto;
import ru.kurakin.dto.epic.NewEpicDto;
import ru.kurakin.enums.TaskStatus;
import ru.kurakin.mappers.EpicMapper;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;
import ru.kurakin.repositories.EpicRepository;
import ru.kurakin.repositories.TaskRepository;
import ru.kurakin.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class EpicService {
    private final EpicRepository epicRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public FullEpicDto saveEpic(NewEpicDto newEpicDto) {
        User coordinator = userRepository.getReferenceById(newEpicDto.getCoordinatorId());
        coordinator.getId();
        List<Task> epicTasks = taskRepository.findAllById(newEpicDto.getTaskIds());
        if (epicTasks.size() != newEpicDto.getTaskIds().size()) {
            throw new IllegalArgumentException("При создании эпика указаны не существующие задачи");
        }
        Set<Task> tasks = new HashSet<>(epicTasks);
        Map<String, Object> calculatedParameters = calculateEpicParameters(epicTasks);
        TaskStatus status = (TaskStatus) calculatedParameters.get("calculatedStatus");
        int duration = (int) calculatedParameters.get("duration");
        LocalDate epicStart = (LocalDate) calculatedParameters.get("epicStart");
        LocalDate epicEnd = (LocalDate) calculatedParameters.get("epicEnd");
        Epic epic = epicRepository.save(EpicMapper.toEpic(newEpicDto, coordinator, tasks, status, duration, epicStart, epicEnd));
        for (Task task : epicTasks) {
            task.setEpic(epic);
        }
        taskRepository.saveAll(epicTasks);
        return EpicMapper.toFullEpicDto(epic);
    }

    private Map<String, Object> calculateEpicParameters(List<Task> tasks) {
        int duration = 0;
        TaskStatus calculatedStatus = null;
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
        assert calculatedStatus != null;
        return Map.of(
                "duration", duration,
                "calculatedStatus", calculatedStatus,
                "epicStart", epicStart,
                "epicEnd", epicEnd
        );
    }
}
