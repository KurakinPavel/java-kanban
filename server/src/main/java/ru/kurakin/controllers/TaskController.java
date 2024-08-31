package ru.kurakin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kurakin.dto.epic.FullEpicDto;
import ru.kurakin.dto.epic.ShortEpicDto;
import ru.kurakin.dto.task.FullTaskDto;
import ru.kurakin.dto.task.NewTaskDto;
import ru.kurakin.dto.task.UpdateTaskDto;
import ru.kurakin.services.TaskService;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
@AllArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FullTaskDto saveTask(@RequestBody NewTaskDto newTaskDto) {
        log.info("Контроллер сервера получил запрос на добавление новой задачи");
        return taskService.saveTask(newTaskDto);
    }

    @PatchMapping("/{taskId}")
    public FullTaskDto updateTask(@PathVariable Integer taskId,
                                  @RequestBody UpdateTaskDto updateTaskDto) {
        log.info("Запрос на обновление задачи с id={}", taskId);
        return taskService.updateTask(taskId, updateTaskDto);
    }

    @GetMapping("/{taskId}")
    public FullTaskDto getTaskById(@PathVariable Integer taskId) {
        log.info("Запрос на получение задачи с id={}", taskId);
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/without-epics")
    public List<FullTaskDto> getTasksWithoutEpics() {
        log.info("Запрос на получение эпиков, в которых нет задач");
        return taskService.getTaskWithoutEpics();
    }
}
