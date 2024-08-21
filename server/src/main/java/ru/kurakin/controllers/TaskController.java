package ru.kurakin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kurakin.dto.task.FullTaskDto;
import ru.kurakin.dto.task.NewTaskDto;
import ru.kurakin.services.TaskService;

@RestController
@RequestMapping(path = "/tasks")
@AllArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FullTaskDto saveTask(@RequestBody NewTaskDto newTaskDto) {
        log.info("Контроллер сервера получил запрос на добавление нового пользователя");
        return taskService.saveTask(newTaskDto);
    }
}
