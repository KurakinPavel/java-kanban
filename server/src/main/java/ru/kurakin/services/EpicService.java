package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurakin.dto.epic.EpicParametersDto;
import ru.kurakin.dto.epic.FullEpicDto;
import ru.kurakin.dto.epic.NewEpicDto;
import ru.kurakin.dto.epic.UpdateEpicDto;
import ru.kurakin.enums.TaskStatus;
import ru.kurakin.mappers.EpicMapper;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;
import ru.kurakin.repositories.EpicRepository;
import ru.kurakin.repositories.TaskRepository;
import ru.kurakin.repositories.UserRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
        for (Task task : epicTasks) {
            if (task.getEpic() != null) {
                throw new IllegalArgumentException("При создании эпика указана задача, относящаяся к существующему эпику");
            }
        }
        Set<Task> tasks = new HashSet<>(epicTasks);
        EpicParametersDto calculatedParameters = EpicMapper.calculateEpicParameters(epicTasks);
        TaskStatus status = calculatedParameters.getCalculatedStatus();
        int duration = calculatedParameters.getDuration();
        LocalDate epicStart = calculatedParameters.getEpicStart();
        LocalDate epicEnd = calculatedParameters.getEpicEnd();
        Epic epic = epicRepository.save(EpicMapper.toEpic(newEpicDto, coordinator, tasks, status, duration, epicStart, epicEnd));
        for (Task task : epicTasks) {
            task.setEpic(epic);
        }
        taskRepository.saveAll(epicTasks);
        return EpicMapper.toFullEpicDto(epic);
    }

    @Transactional
    public FullEpicDto updateEpic(int epicId, UpdateEpicDto updateEpicDto) {
        Epic updatingEpic = epicRepository.getReferenceById(epicId);
        updatingEpic.getId();
        if (updateEpicDto.getCoordinatorId() != null) {
            User newCoordinator = userRepository.getReferenceById(updateEpicDto.getCoordinatorId());
            newCoordinator.getId();
            updatingEpic.setCoordinator(newCoordinator);
        }
        if (updateEpicDto.getTitle() != null) {
            if (updateEpicDto.getTitle().length() < 10) {
                throw new IllegalArgumentException("Заглавие обновляемого эпика не может быть меньше 10 символов");
            }
            updatingEpic.setTitle(updateEpicDto.getTitle());
        }
        if (updateEpicDto.getDescription() != null) {
            if (updateEpicDto.getDescription().length() < 10) {
                throw new IllegalArgumentException("Описание обновляемого эпика не может быть меньше 10 символов");
            }
            updatingEpic.setDescription(updateEpicDto.getDescription());
        }
        return EpicMapper.toFullEpicDto(epicRepository.save(updatingEpic));
    }

    @Transactional
    public FullEpicDto removeTaskFromEpic(int epicId, int taskId) {
        Epic epic = epicRepository.getReferenceById(epicId);
        Task task = taskRepository.getReferenceById(taskId);
        if (task.getEpic().getId() != epicId) {
            throw new IllegalArgumentException("У подзадачи с id " + taskId + " нет связи с эпиком с id " + epicId);
        }
        Set<Task> epicTasks = epic.getTasks();
        boolean resultOfRemoving = epicTasks.remove(task);
        if (resultOfRemoving) {
            task.setEpic(null);
            EpicParametersDto calculatedParameters = EpicMapper.calculateEpicParameters(epicTasks.stream().toList());
            epic.setDuration(calculatedParameters.getDuration());
            epic.setStatus(calculatedParameters.getCalculatedStatus());
            epic.setStartTime(calculatedParameters.getEpicStart());
            epic.setEndTime(calculatedParameters.getEpicEnd());
            epicRepository.save(epic);
            taskRepository.save(task);
            log.info("Подзадача с id {} удалена из эпика с id {}", taskId, epicId);
        } else {
            throw new IllegalArgumentException("Удалить подзадачу с id " + taskId + " из эпика с id " + epicId + " не удалось");
        }
        return EpicMapper.toFullEpicDto(epic);
    }

    @Transactional
    public FullEpicDto addTaskToEpic(int epicId, int taskId) {
        Epic epic = epicRepository.getReferenceById(epicId);
        Task task = taskRepository.getReferenceById(taskId);
        if (task.getEpic() != null) {
            throw new IllegalArgumentException("Подзадача с id " + taskId + " уже связана с эпиком с id " + task.getEpic().getId());
        }
        Set<Task> epicTasks = epic.getTasks();
        boolean resultOfAdding = epicTasks.add(task);
        if (resultOfAdding) {
            task.setEpic(epic);
            EpicParametersDto calculatedParameters = EpicMapper.calculateEpicParameters(epicTasks.stream().toList());
            epic.setDuration(calculatedParameters.getDuration());
            epic.setStatus(calculatedParameters.getCalculatedStatus());
            epic.setStartTime(calculatedParameters.getEpicStart());
            epic.setEndTime(calculatedParameters.getEpicEnd());
            epicRepository.save(epic);
            taskRepository.save(task);
            log.info("Подзадача с id {} добавлена в эпик с id {}", taskId, epicId);
        } else {
            throw new IllegalArgumentException("Добавить подзадачу с id " + taskId + " в эпик с id " + epicId + " не удалось");
        }
        return EpicMapper.toFullEpicDto(epic);
    }
}
