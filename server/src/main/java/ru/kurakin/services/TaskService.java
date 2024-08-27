package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurakin.dto.Constants;
import ru.kurakin.dto.epic.EpicParametersDto;
import ru.kurakin.dto.task.FullTaskDto;
import ru.kurakin.dto.task.NewTaskDto;
import ru.kurakin.dto.task.UpdateTaskDto;
import ru.kurakin.enums.TaskStatus;
import ru.kurakin.mappers.EpicMapper;
import ru.kurakin.mappers.TaskMapper;
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
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final EpicRepository epicRepository;
    private final UserRepository userRepository;

    @Transactional
    public FullTaskDto saveTask(NewTaskDto newTaskDto) {
        Epic epic = null;
        if (newTaskDto.getEpicId() != null && newTaskDto.getEpicId() > 0) {
            epic = epicRepository.getReferenceById(newTaskDto.getEpicId());
        }
        User performer = userRepository.getReferenceById(newTaskDto.getPerformerId());
        performer.getId();
        Set<Task> dependentTasks = new HashSet<>();
        if (newTaskDto.getDependentTaskIds() != null && !newTaskDto.getDependentTaskIds().isEmpty()) {
            dependentTasks.addAll(taskRepository.findAllById(newTaskDto.getDependentTaskIds()));
            if (dependentTasks.size() != newTaskDto.getDependentTaskIds().size()) {
                throw new IllegalArgumentException("При создании задания указаны не существующие зависящие задачи");
            }
        }
        return TaskMapper.toFullTaskDto(taskRepository.save(TaskMapper.toTask(newTaskDto, epic, performer, dependentTasks)));
    }

    @Transactional
    public FullTaskDto updateTask(int taskId, UpdateTaskDto updateTaskDto) {
        Task updatingTask = taskRepository.getReferenceById(taskId);
        if (updateTaskDto.getPerformerId() != null) {
            User performer = userRepository.getReferenceById(updateTaskDto.getPerformerId());
            performer.getId();
            updatingTask.setPerformer(performer);
        }
        if (updateTaskDto.getDependentTaskIds() != null) {
            Set<Task> updatingDependentTasks = new HashSet<>();
            if (updateTaskDto.getDependentTaskIds().isEmpty()) {
                updatingTask.setDependentTasks(updatingDependentTasks);
            } else {
                updatingDependentTasks.addAll(taskRepository.findAllById(updateTaskDto.getDependentTaskIds()));
                if (updatingDependentTasks.size() != updateTaskDto.getDependentTaskIds().size()) {
                    throw new IllegalArgumentException("При обновлении задачи указаны не существующие зависящие задачи");
                }
                updatingTask.setDependentTasks(updatingDependentTasks);
            }
        }
        if (updateTaskDto.getTitle() != null) {
            if (updateTaskDto.getTitle().length() < 10) {
                throw new IllegalArgumentException("Заглавие обновляемой задачи не может быть меньше 10 символов");
            }
            updatingTask.setTitle(updateTaskDto.getTitle());
        }
        if (updateTaskDto.getDescription() != null) {
            if (updateTaskDto.getDescription().length() < 10) {
                throw new IllegalArgumentException("Описание обновляемой задачи не может быть меньше 10 символов");
            }
            updatingTask.setDescription(updateTaskDto.getDescription());
        }
        if (updateTaskDto.getStatus() != null) {
            TaskStatus status = TaskStatus.from(updateTaskDto.getStatus())
                    .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + updateTaskDto.getStatus()));
            updatingTask.setStatus(status);
        }
        if (updateTaskDto.getStartDate() != null) {
            updatingTask.setStartDate(LocalDate.parse(updateTaskDto.getStartDate(), Constants.DATE_FORMAT));
        }
        if (updateTaskDto.getDuration() != null) {
            updatingTask.setDuration(updateTaskDto.getDuration());
        }
        if (updateTaskDto.getStartDate() != null || updateTaskDto.getDuration() != null) {
            updatingTask.setEndDate(updatingTask.getStartDate().plusDays(updatingTask.getDuration()));
        }
        if (updatingTask.getEpic() != null && updateTaskDto.getStatus() != null ||
                updateTaskDto.getStartDate() != null || updateTaskDto.getDuration() != null) {
            Epic updatingEpic = updatingTask.getEpic();
            assert updatingEpic != null;
            List<Task> updatingEpicTasks = new ArrayList<>(updatingEpic.getTasks());
            EpicParametersDto calculatedParameters = EpicMapper.calculateEpicParameters(updatingEpicTasks);
            updatingEpic.setDuration(calculatedParameters.getDuration());
            updatingEpic.setStatus(calculatedParameters.getCalculatedStatus());
            updatingEpic.setStartTime(calculatedParameters.getEpicStart());
            updatingEpic.setEndTime(calculatedParameters.getEpicEnd());
            epicRepository.save(updatingEpic);
        }
        return TaskMapper.toFullTaskDto(taskRepository.save(updatingTask));
    }

}
