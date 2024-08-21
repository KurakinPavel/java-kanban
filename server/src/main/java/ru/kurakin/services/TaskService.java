package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurakin.dto.task.FullTaskDto;
import ru.kurakin.dto.task.NewTaskDto;
import ru.kurakin.mappers.TaskMapper;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;
import ru.kurakin.repositories.EpicRepository;
import ru.kurakin.repositories.TaskRepository;
import ru.kurakin.repositories.UserRepository;

import java.util.HashSet;
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
        Epic epic = epicRepository.getReferenceById(newTaskDto.getEpicId());
        User performer = userRepository.getReferenceById(newTaskDto.getPerformerId());
        performer.getId();
        Set<Task> dependentTasks = new HashSet<>(taskRepository.findAllById(newTaskDto.getDependentTaskIds()));
        if (dependentTasks.size() != newTaskDto.getDependentTaskIds().size()) {
            throw new IllegalArgumentException("При создании задания указаны не существующие зависящие задачи");
        }
        return TaskMapper.toFullTaskDto(taskRepository.save(TaskMapper.toTask(newTaskDto, epic, performer, dependentTasks)));
    }
}
